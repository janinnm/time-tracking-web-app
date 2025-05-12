'use client';

import { useState, useMemo } from 'react';
import type { Record } from '@/types/record';
import { Card, CardContent } from '@/components/ui/card';

type Props = {
  records: Record[];
  loading: boolean;
  onDelete: (id: number) => Promise<void>;
};

const ITEMS_PER_PAGE = 5;

export default function RecordsTable({ records, loading, onDelete }: Props) {
  const [currentPage, setCurrentPage] = useState(1);
  const [categoryFilter, setCategoryFilter] = useState('');
  const [dateFilter, setDateFilter] = useState('');

const normalizeDateToUTC = (date: string | Date): Date => {
  const normalizedDate = new Date(date);
  normalizedDate.setHours(0, 0, 0, 0);
  return normalizedDate;
};

const filteredRecords = useMemo(() => {
  return records.filter((r) => {
    const matchesCategory =
      !categoryFilter || r.categoryName.toLowerCase().includes(categoryFilter.toLowerCase());

    const recordDate = normalizeDateToUTC(r.date);
    const filterDate = normalizeDateToUTC(dateFilter);

    const matchesDate = !dateFilter || recordDate.getTime() === filterDate.getTime();

    return matchesCategory && matchesDate;
  });
}, [records, categoryFilter, dateFilter]);

  const paginatedRecords = filteredRecords.slice(
    (currentPage - 1) * ITEMS_PER_PAGE,
    currentPage * ITEMS_PER_PAGE
  );

  const totalPages = Math.ceil(filteredRecords.length / ITEMS_PER_PAGE);

  return (
    <Card className="bg-white text-gray-800 mt-6 border border-gray-200 shadow-sm">
      <CardContent className="p-6">
        <div className="mb-4">
          <h2 className="text-xl font-semibold">Records</h2>
          <p className="text-sm text-gray-500">List of your recent time logs</p>
        </div>

        {/* Filters */}
        <div className="flex flex-col sm:flex-row gap-3 mb-4">
          <input
            type="text"
            placeholder="Filter by category"
            value={categoryFilter}
            onChange={(e) => {
              setCurrentPage(1);
              setCategoryFilter(e.target.value);
            }}
            className="border border-gray-300 rounded px-3 py-2 text-sm"
          />
          <input
            type="date"
            value={dateFilter}
            onChange={(e) => {
              setCurrentPage(1);
              setDateFilter(e.target.value);
            }}
            className="border border-gray-300 rounded px-3 py-2 text-sm"
          />
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
                    <th className="px-4 py-3 text-left font-medium text-gray-700">Date</th>
                    <th className="px-4 py-3 text-left font-medium text-gray-700">Actions</th>
                  </tr>
                </thead>
                <tbody className="divide-y divide-gray-100">
                  {paginatedRecords.map((record) => (
                    <tr key={record.recordId} className="hover:bg-gray-50 transition">
                      <td className="px-4 py-3">{record.title}</td>
                      <td className="px-4 py-3">{record.hours}</td>
                      <td className="px-4 py-3">{record.categoryName.charAt(0).toUpperCase() + record.categoryName.slice(1)}</td>
                      <td className="px-4 py-3">{new Date(record.date).toLocaleDateString()}</td>
                      <td className="px-4 py-3">
                        <button
                          onClick={() => onDelete(record.recordId)}
                          className="text-red-600 hover:underline text-sm"
                        >
                          Delete
                        </button>
                      </td>
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
