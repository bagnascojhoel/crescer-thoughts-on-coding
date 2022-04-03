import { API_CONTEXT } from '../base-api/base-api.hook';
import { usePrivateBaseApi } from '../private-base-api/private-base-api.hook';

export function usePostApi() {
  const { post, get } = usePrivateBaseApi(API_CONTEXT.POST);

  const createPost = async (body) => {
    return await post('', body);
  };

  const createComment = async (postId, body) => {
    return await post(`/${postId}`, body);
  };

  const updateVisibility = async (postId, visibility) => {
    return await post(`/update-visibility/${postId}?visibility=${visibility}`);
  };

  const getMyTimeline = async (page) => {
    return await get(`?page=${page}`);
  };

  const getSomeonesTimeline = async (email, page) => {
    return await get(`/${email}?page=${page}`);
  };

  const likePost = async (postId) => {
    return await post(`/like/${postId}`);
  };

  const unlikePost = async (postId) => {
    return await post(`/unlike/${postId}`);
  };

  return {
    createPost,
    createComment,
    updateVisibility,
    getMyTimeline,
    getSomeonesTimeline,
    likePost,
    unlikePost,
  };
}

export const POST_VISIBILITY = {
  PRIVATE: 'PRIVATE',
  PUBLIC: 'PUBLIC',
};
