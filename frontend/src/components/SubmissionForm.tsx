'use client';
import { useState } from 'react';

type Props = {
  onSubmit: (input: string) => Promise<void>;
  message: string;
  error: string;
  setMessage: (msg: string) => void;
  setError: (msg: string) => void;
};

export default function SubmissionForm({
  onSubmit,
  error,
  message,
  setMessage,
  setError,
}: Props) {
  const [input, setInput] = useState('');

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setMessage('');
    setError('');
    await onSubmit(input.trim());
  };

  return (
    <div className="bg-white p-6 rounded-2xl shadow-sm border border-gray-200">
      <h2 className="text-lg font-semibold text-gray-800 mb-3">Submit Entry</h2>
      <form
        onSubmit={handleSubmit}
        className="flex flex-col sm:flex-row items-stretch sm:items-center gap-3"
      >
        <input
          type="text"
          value={input}
          onChange={(e) => setInput(e.target.value)}
          className="flex-1 border border-gray-300 rounded-lg px-4 py-2 focus:outline-none focus:ring-2 focus:ring-black transition"
          placeholder="Enter something..."
        />
        <button
          type="submit"
          className="bg-black text-white px-4 py-2 rounded-lg hover:bg-gray-800 transition"
        >
          Submit
        </button>
      </form>
      {message && <p className="mt-3 text-sm text-green-600">{message}</p>}
      {error && <p className="mt-3 text-sm text-red-600">{error}</p>}
    </div>
  );
}
