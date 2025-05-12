import { clsx, type ClassValue } from "clsx"
import { twMerge } from "tailwind-merge"
import { Record } from '@/types/record';

export function cn(...inputs: ClassValue[]) {
  return twMerge(clsx(inputs))
}

export function groupRecords(records: Record[], groupBy: 'date' | 'project' | 'both') {
  const grouped: { [key: string]: number } = {};

  records.forEach((record) => {
    const project = record.categoryName;
    const date = record.date.toISOString().slice(0, 10);

    let key = '';
    if (groupBy === 'date') {
      key = date;
    } else if (groupBy === 'project') {
      key = project;
    } else {
      key = `${project} - ${date}`;
    }

    grouped[key] = (grouped[key] || 0) + record.hours;
  });

  return Object.entries(grouped).map(([key, hours]) => ({ label: key, hours }));
}

