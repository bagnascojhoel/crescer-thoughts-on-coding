import { API_CONTEXT } from '../base-api/base-api.hook';
import { usePrivateBaseApi } from '../private-base-api/private-base-api.hook';

export function useFriendshipApi() {
  const { get, post } = usePrivateBaseApi(API_CONTEXT.FRIENDSHIP);

  const listMyFriends = async () => {
    return await get('/');
  };

  const listMyFriendshipRequests = async () => {
    return await get('/requests');
  };

  const findFriendship = async (email) => {
    return await get(`/${email}`);
  };

  const sendRequest = async (email) => {
    return await post(`/requests/${email}`);
  };

  const acceptRequest = async (friendshipId) => {
    return await post(`/requests/accept/${friendshipId}`);
  };

  const denyRequest = async (friendshipId) => {
    return await post(`/requests/deny/${friendshipId}`);
  };

  const undoFriendship = async (email) => {
    return await post(`/undo/${email}`);
  }

  return {
    listMyFriends,
    listMyFriendshipRequests,
    findFriendship,
    sendRequest,
    acceptRequest,
    denyRequest,
    undoFriendship,
  };
}

export const FRIENDSHIP_STATUS = {
  PENDING: 'PENDING',
  ACCEPTED: 'ACCEPTED',
  DENIED: 'DENIED',
};
