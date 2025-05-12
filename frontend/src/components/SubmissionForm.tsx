'use client';
import { useState } from "react";
import { createRecord } from "@/lib/api";

export default function SubmissionForm() {
  const [input, setInput] = useState("");
  const [message, setMessage] = useState("");

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    if (!input.trim()) return;

    try {
      await createRecord(input.trim());
      setMessage("✅ Submission successful!");
      setInput("");
    } catch (error) {
      console.error("Error submitting:", error);
      setMessage("❌ Failed to submit.");
    }
  };

  return (
    <div className="bg-white p-6 rounded-2xl shadow-sm border border-gray-200">
      <h2 className="text-lg font-semibold text-gray-800 mb-3">Submit Entry</h2>
      <form onSubmit={handleSubmit} className="flex flex-col sm:flex-row items-stretch sm:items-center gap-3">
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
      {message && (
        <p className="mt-3 text-sm text-gray-600">{message}</p>
      )}
    </div>
  );
}
