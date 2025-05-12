import { json } from "stream/consumers";

const BASE_URL = 'http://localhost:8080';

export async function login(username: string, password: string) {
  const res = await fetch(`${BASE_URL}/auth/login`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify({ username, password }),
  });

  if (!res.ok) {
    const msg = await res.text();
    throw new Error(msg || 'Login failed');
  }

  const data = await res.json();
  const token = data.data.token;
  console.log(token);

  localStorage.setItem('authToken', token);

  return token;
}


export async function checkAuth() {
  const token = localStorage.getItem('authToken');
  
  if (!token) {
    throw new Error('No token found');
  }

  const res = await fetch(`${BASE_URL}/auth/status`, {
    method: 'POST',
    headers: {
      'Authorization': `Bearer ${token}`,
    },
  });

  if (!res.ok) {
    throw new Error('Not authenticated');
  }

  const data = await res.text();
  return data === 'Authenticated';
}


export async function signup(username: string, password: string, email: string, role: string) {
  const res = await fetch(`${BASE_URL}/auth/signup`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify({ username, password, email, role }),
  });

  const data = await res.json();
  if (res.ok) {
    return data.result;
  }

  throw new Error(data?.message || 'Signup failed');
}

export const createRecord = async (recordData: string) => {
  const token = localStorage.getItem('authToken');

  if (!token) {
    throw new Error('No token found');
  }

  const response = await fetch(`${BASE_URL}/records`, {
    method: 'POST',
    body: JSON.stringify(recordData),
    headers: {
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}`,
    },
  });

  const text = await response.text();
  const data = text ? JSON.parse(text) : {};

  if (!response.ok) {
    const errorMessage = data.message || 'Failed to create record';
    throw new Error(errorMessage);
  }

  return data;
};



export const updateRecord = async (recordId: number, recordData: string) => {
  const token = localStorage.getItem('authToken');

  if (!token) {
    throw new Error('No token found');
  }

  const response = await fetch(`${BASE_URL}/records/${recordId}`, {
    method: 'PUT',
    body: JSON.stringify(recordData),
    headers: {
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}`,
    },
  });

  if (!response.ok) {
    throw new Error('Failed to update record');
  }

  return response.json();
};


export const deleteRecord = async (recordId: number) => {
  const token = localStorage.getItem('authToken');

  if (!token) {
    throw new Error('No token found');
  }

  const response = await fetch(`${BASE_URL}/records/${recordId}`, {
    method: 'DELETE',
    headers: {
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}`,
    },
  });

  if (!response.ok) {
    throw new Error('Failed to delete record');
  }

  return response.json();
};


export const getRecordById = async (recordId: number) => {
  const token = localStorage.getItem('authToken');

  if (!token) {
    throw new Error('No token found');
  }

  const response = await fetch(`${BASE_URL}/records/${recordId}`, {
    method: 'GET',
    headers: {
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}`,
    },
  });

  if (!response.ok) {
    throw new Error('Failed to fetch record');
  }

  return response.json();
};

export const getAllRecords = async () => {
  console.log("getAllRecords");
  const token = localStorage.getItem('authToken');

  console.log("hello");
  console.log(token);
  if (!token) {
    throw new Error('No token found');
  }

  const response = await fetch(`${BASE_URL}/records`, {
    method: 'GET',
    headers: {
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}`,
    },
  });

  if (!response.ok) {
    throw new Error('Failed to fetch records');
  }

  const result = await response.json();
  console.log("Records API response:", result);

  return result.data;
};

