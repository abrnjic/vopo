"use client";

import { useEffect } from 'react';

export default function Error({
  error,
  reset,
}: {
  error: Error & { digest?: string };
  reset: () => void;
}) {
  useEffect(() => {
    console.error(error);
  }, [error]);

  return (
    <div className="min-h-screen bg-gray-900 flex items-center justify-center p-4">
      <div className="bg-red-900/50 border border-red-500 p-8 rounded-xl max-w-lg w-full text-center">
        <h2 className="text-2xl font-bold text-white mb-4">Nešto je pošlo po zlu!</h2>
        <p className="text-red-300 mb-4 font-mono text-left bg-black/30 p-4 rounded text-sm overflow-auto">
          {error.message || 'Nepoznata greška'}
        </p>
        <button
          onClick={() => reset()}
          className="bg-blue-600 hover:bg-blue-700 text-white px-6 py-2 rounded-lg font-medium"
        >
          Pokušaj ponovno
        </button>
      </div>
    </div>
  );
}
