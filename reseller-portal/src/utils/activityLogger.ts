import { collection, addDoc } from 'firebase/firestore';
import { db } from '../firebase';

export type ActivityAction = 
  | 'CREATE_LICENSE' 
  | 'EXTEND_LICENSE' 
  | 'DELETE_LICENSE' 
  | 'BULK_EXTEND'
  | 'BULK_DELETE'
  | 'ADD_CREDITS'
  | 'CREATE_RESELLER'
  | 'LOGIN'
  | 'UPDATE_PROFILE'
  | 'ADD_DOMAIN'
  | 'DELETE_DOMAIN'
  | 'UPDATE_STATUS'
  | 'DELETE_USER'
  | 'PASSWORD_RESET'
  | 'EDIT_RESELLER'
  | 'REMOVE_CREDITS';

export interface ActivityLog {
  userId: string;
  userEmail: string;
  role: 'admin' | 'reseller';
  action: ActivityAction;
  details: string;
  timestamp: number;
}

export const logActivity = async (
  userId: string,
  userEmail: string,
  role: 'admin' | 'reseller',
  action: ActivityAction,
  details: string
) => {
  try {
    const logRef = collection(db, 'activity_logs');
    await addDoc(logRef, {
      userId,
      userEmail,
      role,
      action,
      details,
      timestamp: Date.now()
    });
  } catch (error) {
    console.error("Failed to log activity:", error);
  }
};
