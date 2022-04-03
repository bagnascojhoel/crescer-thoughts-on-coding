import axios from 'axios';

export const API_CONTEXT = {
  AUTH: '/public/auth',
  USER: '/private/user',
  FRIENDSHIP: '/private/friendship',
  POST: '/private/post',
};

export function useBaseApi(context, headers) {
  if (context && !contextExists(context)) throw "This context doesn't exist.";

  const contextPath = context ? `${context}` : '';
  const baseApi = axios.create({
    baseURL: `http://localhost:8090/tcc-java${contextPath}`,
    headers,
  });

  const post = async (path, body) => {
    try {
      const response = await baseApi.post(path, body);
      return response?.data || {};
    } catch (error) {
      
      return error?.response?.data || {};
    }
  };

  const get = async (path) => {
    try {
      const response = await baseApi.get(path);
      return response?.data || {};
    } catch (error) {

      return error?.response?.data || {};
    }
  };

  return {
    post,
    get,
  };
}

function contextExists(context) {
  const contextList = Object.values(API_CONTEXT);

  return contextList.some((c) => c === context);
}
