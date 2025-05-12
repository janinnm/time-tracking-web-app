import { clsx, type ClassValue } from "clsx"
import { twMerge } from "tailwind-merge"
import { Record } from '@/types/record';

export function cn(...inputs: ClassValue[]) {
  return twMerge(clsx(inputs))
}

export function groupRecords(records: Record[], groupBy: 'date' | 'project' | 'both') {
  const grouped: { [key: string]: number } = {};

  records.forEach((record) => {
    const dateStr = new Date(record.date.join('-')).toLocaleDateString();
    const project = record.category.name;

    let key = '';
    if (groupBy === 'date') key = dateStr;
    else if (groupBy === 'project') key = project;
    else key = `${project} - ${dateStr}`;

    grouped[key] = (grouped[key] || 0) + record.hours;
  });

  return Object.entries(grouped).map(([key, hours]) => ({ label: key, hours }));
}
