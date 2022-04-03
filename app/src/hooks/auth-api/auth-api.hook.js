import { API_CONTEXT, useBaseApi } from '../base-api/base-api.hook';

export function useAuthApi() {
  const { post } = useBaseApi(API_CONTEXT.AUTH);

  const login = async (email, password) =>
    await post('/login', { email, password });

  const logout = async () => {};

  const signUp = async (user) => await post('/sign-up', user);

  return {
    login,
    signUp,
    logout,
  };
}
