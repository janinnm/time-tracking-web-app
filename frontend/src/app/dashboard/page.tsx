'use client';

import SubmissionForm from '@/components/SubmissionForm';
import RecordsTable from '@/components/RecordsTable';
import HoursChart from '@/components/HoursChart';
import Header from '@/components/Header';
import { useEffect, useState } from 'react';
import { createRecord, deleteRecord, getAllRecords } from '@/lib/api';
import { Record } from '@/types/record';

const DashboardPage = () => {
  const [records, setRecords] = useState<Record[]>([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');
  const [message, setMessage] = useState('');

const handleCreate = async (input: string) => {
  try {
    const data = await createRecord(input);
    const updated = await getAllRecords();
    setRecords(updated);
    setMessage(data.message || 'Record created successfully');
  } catch (err: unknown) {
    if (err instanceof Error) {
      setError(err.message);
    } else {
      setError('Failed to create record');
    }
  }
};


  const handleDelete = async (id: number) => {
    try {
      setRecords((prev) => prev.filter((r) => r.recordId !== id));
      await deleteRecord(id);
    } catch (err: unknown) {
      console.error('Failed to delete record:', err);
    }
  };

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
            <SubmissionForm
              onSubmit={handleCreate}
              setError={setError}
              setMessage={setMessage}
              message={message}
              error={error}
            />
          </div>

          <div className="bg-white border border-gray-200 rounded-lg shadow-sm p-4 lg:col-span-2">
            <HoursChart records={records} />
          </div>

          <div className="bg-white border border-gray-200 rounded-lg shadow-sm p-4 lg:col-span-3">
            <RecordsTable
              records={records}
              loading={loading}
              onDelete={handleDelete}
            />
          </div>
        </div>
      </main>
    </div>
  );
};

export default DashboardPage;
