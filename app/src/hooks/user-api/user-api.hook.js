import { API_CONTEXT } from '../base-api/base-api.hook';
import { usePrivateBaseApi } from '../private-base-api/private-base-api.hook';

export function useUserApi() {
  const { get, post } = usePrivateBaseApi(API_CONTEXT.USER);

  const findMyself = async () => {
    return await get('');
  };

  const update = async (name, nickname, profilePhoto) => {
    return await post('/edit', { name, nickname, profilePhoto });
  };

  const findByNameOrEmail = async (query) => {
    return await get(`/search?query=${query}`);
  };

  const findByEmail = async (email) => {
    return await get(email);
  };

  return {
    findMyself,
    update,
    findByNameOrEmail,
    findByEmail,
  };
}
