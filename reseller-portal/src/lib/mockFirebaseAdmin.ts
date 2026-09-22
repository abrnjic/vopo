export const mockState = {
  users: new Map(),
  authUsers: new Map(),
  settings: new Map(),
  licenses: new Map(),
  transactions: new Map(),
  activity_logs: new Map(),
  security_logs: new Map(),
  device_diagnostics: new Map(),
  rate_limits: new Map(),
  notifications: new Map(),
  support_tickets: new Map(),
  backup_runs: new Map(),
  throwAuthError: false,
  throwDbError: false
};

const createMockDoc = (id: string, dataMap: Map<string, any>) => ({
  exists: dataMap.has(id),
  data: () => dataMap.get(id),
  id
});

export const mockAdminDb = {
  collection: (colName: string) => ({
    get: async () => {
      if (mockState.throwDbError) throw new Error('Mock DB Error');
      const dataMap = (mockState as any)[colName];
      const docs = [...dataMap.entries()].map(([id]) => createMockDoc(id as string, dataMap));
      return { size: docs.length, docs, forEach: (cb: any) => docs.forEach(cb) };
    },
    doc: (docId?: string) => {
      const id = docId || `auto-id-${Math.random()}`;
      const dataMap = (mockState as any)[colName];
      return {
        id,
        get: async () => {
          if (mockState.throwDbError) throw new Error('Mock DB Error');
          return createMockDoc(id, dataMap);
        },
        update: async (data: any) => {
          if (mockState.throwDbError) throw new Error('Mock DB Error');
          if (!dataMap.has(id)) throw new Error('NOT_FOUND');
          dataMap.set(id, { ...dataMap.get(id), ...data });
        },
        delete: async () => {
          if (mockState.throwDbError) throw new Error('Mock DB Error');
          dataMap.delete(id);
        },
        set: (data: any, options?: any) => {
          if (mockState.throwDbError) throw new Error('Mock DB Error');
          if (options?.merge && dataMap.has(id)) {
            dataMap.set(id, { ...dataMap.get(id), ...data });
          } else {
            dataMap.set(id, data);
          }
        }
      };
    },
    where: (field: string, op: string, value: any) => {
      // Very basic mock for where clause, chaining where
      const filterDocs = (dataMap: Map<string, any>, conditions: any[]) => {
        const matches: any[] = [];
        for (const [id, data] of dataMap.entries()) {
          let pass = true;
          for (const cond of conditions) {
            if (data[cond.field] !== cond.value) {
              pass = false;
              break;
            }
          }
          if (pass) matches.push(createMockDoc(id, dataMap));
        }
        return {
          size: matches.length,
          docs: matches,
          forEach: (cb: any) => matches.forEach(cb)
        };
      };
      
      const createWhere = (conditions: any[]) => ({
        where: (f: string, o: string, v: any) => createWhere([...conditions, { field: f, op: o, value: v }]),
        get: async () => {
          if (mockState.throwDbError) throw new Error('Mock DB Error');
          return filterDocs((mockState as any)[colName], conditions);
        }
      });
      
      return createWhere([{ field, op, value }]);
    }
  }),
  runTransaction: async (callback: any) => {
    // Simple mock that just executes the callback with a mock transaction object
    const transaction = {
      get: async (ref: any) => ref.get(),
      update: (ref: any, data: any) => ref.update(data),
      set: (ref: any, data: any, opts: any) => ref.set(data, opts),
      delete: (ref: any) => ref.delete()
    };
    try {
      return await callback(transaction);
    } catch (e: any) {
      throw e;
    }
  }
};

export const mockAdminAuth = {
  createUser: async (properties: any) => {
    if (mockState.throwAuthError) throw new Error('Mock Auth Error');
    if ([...mockState.authUsers.values()].some(u => u.email === properties.email)) {
      throw Object.assign(new Error('Email exists'), { code: 'auth/email-already-exists' });
    }
    const uid = `created-${mockState.authUsers.size}`;
    mockState.authUsers.set(uid, { uid, ...properties });
    return { uid, email: properties.email };
  },
  deleteUser: async (uid: string) => { mockState.authUsers.delete(uid); },
  verifyIdToken: async (token: string, _checkRevoked?: boolean) => {
    if (token === 'invalid_token' || token === 'revoked') {
      throw new Error('auth/id-token-revoked');
    }
    const [uid, role, email] = token.split(':');
    return { uid: uid || token, role: role || 'user', email: email || 'test@test.com' };
  },
  getUser: async (uid: string) => {
    if (uid === 'invalid_token') throw new Error('auth/user-not-found');
    const user = mockState.users.get(uid);
    if (!user) throw new Error('auth/user-not-found');
    return { uid, disabled: user.disabled || false };
  },
  setCustomUserClaims: async (uid: string, claims: any) => {
    if (mockState.throwAuthError) throw new Error('Mock Auth Error');
    const user = mockState.users.get(uid);
    if (user) {
      mockState.users.set(uid, { ...user, customClaims: claims });
    }
  },
  updateUser: async (uid: string, properties: any) => {
    if (mockState.throwAuthError) throw new Error('Mock Auth Error');
    const user = mockState.users.get(uid);
    if (user) {
      mockState.users.set(uid, { ...user, ...properties });
    }
  }
};
