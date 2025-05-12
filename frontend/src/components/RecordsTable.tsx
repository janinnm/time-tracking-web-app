'use client';

import { useEffect, useState } from 'react';
import { getAllRecords } from '@/lib/api';
import type { Record } from '@/types/record';
import { Card, CardContent } from '@/components/ui/card';

const ITEMS_PER_PAGE = 5;

export default function RecordsTable() {
  const [records, setRecords] = useState<Record[]>([]);
  const [loading, setLoading] = useState(true);
  const [currentPage, setCurrentPage] = useState(1);

  useEffect(() => {
    async function fetchRecords() {
      try {
        const data = await getAllRecords();
        setRecords(data);
      } catch (err) {
        console.error('Failed to fetch records:', err);
      } finally {
        setLoading(false);
      }
    }

    fetchRecords();
  }, []);

  const paginatedRecords = records.slice(
    (currentPage - 1) * ITEMS_PER_PAGE,
    currentPage * ITEMS_PER_PAGE
  );

  const totalPages = Math.ceil(records.length / ITEMS_PER_PAGE);

  return (
    <Card className="bg-white text-gray-800 mt-6 border border-gray-200 shadow-sm">
      <CardContent className="p-6">
        <div className="mb-4">
          <h2 className="text-xl font-semibold">Records</h2>
          <p className="text-sm text-gray-500">List of your recent time logs</p>
        </div>

        {loading ? (
          <p className="text-gray-500">Loading...</p>
        ) : (
          <>
            <div className="overflow-x-auto">
              <table className="min-w-full text-sm border border-gray-200 rounded">
                <thead className="bg-gray-100 border-b border-gray-200">
                  <tr>
                    <th className="px-4 py-3 text-left font-medium text-gray-700">Title</th>
                    <th className="px-4 py-3 text-left font-medium text-gray-700">Hours</th>
                    <th className="px-4 py-3 text-left font-medium text-gray-700">Category</th>
                  </tr>
                </thead>
                <tbody className="divide-y divide-gray-100">
                  {paginatedRecords.map((record, idx) => (
                    <tr key={idx} className="hover:bg-gray-50 transition">
                      <td className="px-4 py-3">{record.title}</td>
                      <td className="px-4 py-3">{record.hours}</td>
                      <td className="px-4 py-3">{record.category.name}</td>
                    </tr>
                  ))}
                </tbody>
              </table>
            </div>

            <div className="flex justify-between items-center mt-4">
              <button
                onClick={() => setCurrentPage((p) => Math.max(p - 1, 1))}
                disabled={currentPage === 1}
                className="px-3 py-1 text-sm bg-white border border-gray-300 rounded hover:bg-gray-100 disabled:opacity-50"
              >
                Previous
              </button>
              <span className="text-sm text-gray-500">
                Page {currentPage} of {totalPages}
              </span>
              <button
                onClick={() => setCurrentPage((p) => Math.min(p + 1, totalPages))}
                disabled={currentPage === totalPages}
                className="px-3 py-1 text-sm bg-white border border-gray-300 rounded hover:bg-gray-100 disabled:opacity-50"
              >
                Next
              </button>
            </div>
          </>
        )}
      </CardContent>
    </Card>
  );
}
