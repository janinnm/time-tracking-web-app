'use client';

import { useState } from 'react';
import {
  LineChart,
  Line,
  XAxis,
  YAxis,
  Tooltip,
  ResponsiveContainer,
  CartesianGrid,
} from 'recharts';
import { Card, CardContent } from '@/components/ui/card';
import {
  Select,
  SelectContent,
  SelectItem,
  SelectTrigger,
  SelectValue,
} from '@/components/ui/select';
import { Record } from '@/types/record';
import { groupRecords } from '@/lib/utils';

interface HoursChartProps {
  records: Record[];
}

const normalizeDateToUTC = (date: string | Date): Date => {
  const normalizedDate = new Date(date);
  normalizedDate.setDate(normalizedDate.getDate() + 1);
  return normalizedDate;
};

export default function HoursChart({ records }: HoursChartProps) {
  const [groupBy, setGroupBy] = useState<'date' | 'project' | 'both'>('date');

  const normalizedRecords = records.map((record) => ({
    ...record,
    date: normalizeDateToUTC(record.date),
  }));

  const groupedData = groupRecords(normalizedRecords, groupBy);

  return (
    <Card className="bg-white text-gray-800 border border-gray-200 shadow-sm mt-6">
      <CardContent className="p-6">
        <div className="flex items-center justify-between mb-4">
          <div>
            <h2 className="text-xl font-semibold text-gray-900">Total Hours</h2>
            <p className="text-sm text-gray-600">View your logged hours</p>
          </div>
          <Select
            value={groupBy}
            onValueChange={(value: 'date' | 'project' | 'both') => setGroupBy(value)}
          >
            <SelectTrigger className="w-[160px] bg-white border border-gray-300 text-gray-700 hover:bg-gray-50">
              <SelectValue placeholder="Group by" />
            </SelectTrigger>
            <SelectContent className="bg-white text-gray-800 border border-gray-200 shadow">
              <SelectItem value="date">Date</SelectItem>
              <SelectItem value="project">Project</SelectItem>
              <SelectItem value="both">Project + Date</SelectItem>
            </SelectContent>
          </Select>
        </div>

        <ResponsiveContainer width="100%" height={300}>
          <LineChart data={groupedData}>
            <CartesianGrid strokeDasharray="3 3" stroke="#e5e7eb" />
            <XAxis dataKey="label" stroke="#4b5563" />
            <YAxis stroke="#4b5563" />
            <Tooltip
              contentStyle={{ backgroundColor: '#ffffff', borderColor: '#e5e7eb' }}
              labelStyle={{ color: '#1f2937' }}
              itemStyle={{ color: '#1f2937' }}
            />
            <Line
              type="monotone"
              dataKey="hours"
              stroke="#111827"
              dot={{ stroke: '#111827', strokeWidth: 2 }}
            />
          </LineChart>
        </ResponsiveContainer>
      </CardContent>
    </Card>
  );
}
