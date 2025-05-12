'use client';

import SubmissionForm from '@/components/SubmissionForm';
import RecordsTable from '@/components/RecordsTable';
import HoursChart from '@/components/HoursChart';
import Header from '@/components/Header';
import { useEffect, useState } from 'react';
import { getAllRecords } from '@/lib/api';
import { Record } from '@/types/record';

const DashboardPage = () => {
  const [records, setRecords] = useState<Record[]>([]);
  const [loading, setLoading] = useState(true);

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

  return (
    <div className="min-h-screen bg-gray-50">
      <Header />

      <main className="p-4">
        <div className="container mx-auto grid grid-cols-1 lg:grid-cols-3 gap-4">
          <div className="bg-white border border-gray-200 rounded-lg shadow-sm p-4 lg:col-span-1">
            <SubmissionForm />
          </div>

          <div className="bg-white border border-gray-200 rounded-lg shadow-sm p-4 lg:col-span-2">
            <HoursChart records={records} />
          </div>

          <div className="bg-white border border-gray-200 rounded-lg shadow-sm p-4 lg:col-span-3">
            <RecordsTable />
          </div>
        </div>
      </main>
    </div>
  );
};

export default DashboardPage;
