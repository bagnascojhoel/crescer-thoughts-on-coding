import React, { useEffect, useState } from 'react';
import { useLocation, useParams } from 'react-router-dom';
import {
  useUserApi,
  useFriendshipApi,
  usePostApi,
  POST_VISIBILITY,
} from '../../../hooks';
import {
  LayoutContainer,
  PostCreationForm,
  PostForm,
  Profile,
  Timeline,
} from '../../components';

export function ProfileScreen() {
  const [user, setUser] = useState(null);
  const [isMyself, setIsMyself] = useState(false);
  const [myself, setMyself] = useState(null);
  const [existFriendship, setExistFriendship] = useState(false);
  const [posts, setPosts] = useState([]);
  const [page, setPage] = useState(0);
  const { findMyself, findByEmail } = useUserApi();
  const { findFriendship, sendRequest, undoFriendship } = useFriendshipApi();
  const {
    getMyTimeline,
    getSomeonesTimeline,
    likePost,
    unlikePost,
    updateVisibility,
    createComment,
  } = usePostApi();
  const { email } = useParams();
  const { pathname } = useLocation();

  useEffect(() => {
    (async () => {
      const _myself = await findMyself();
      setMyself(_myself);
    })();

    loadContent();
  }, [pathname]);

  const loadContent = () => {
    if (email) ofSomeoneElse(email);
    else ofMyself();
  };

  const ofMyself = async () => {
    const _myself = await findMyself();
    setUser(_myself);

    const _posts = await getMyTimeline(page);

    setPosts(_posts);
    setIsMyself(true);
    setExistFriendship(false);
  };

  const ofSomeoneElse = async () => {
    const user = await findByEmail(email);
    setUser(user);

    const friendship = await findFriendship(email);
    setIsMyself(false);
    setExistFriendship(!!friendship.status);

    const _posts = await getSomeonesTimeline(email, page);
    setPosts(_posts);
  };

  const handleSendRequest = async () => {
    await sendRequest(email);

    setExistFriendship(true);
  };

  const handleUndoFriendship = async () => {
    await undoFriendship(email);

    setExistFriendship(false);
  };

  const handleCreatePost = () => loadContent();

  const handleLike = async (post) => {
    if (post.hasMyLike) await unlikePost(post.id);
    else await likePost(post.id);

    loadContent();
  };

  const handleUpdateVisibility = async (post) => {
    const newVisibility =
      post.visibility === POST_VISIBILITY.PRIVATE
        ? POST_VISIBILITY.PUBLIC
        : POST_VISIBILITY.PRIVATE;

    await updateVisibility(post.id, newVisibility);

    loadContent();
  };

  const handleCreateComment = async ({ postId, content, visibility }) => {
    await createComment(postId, { content, visibility });
    loadContent();
  };

  return (
    <LayoutContainer>
      <Profile
        user={user}
        isMyself={isMyself}
        existFriendship={existFriendship}
        onSendRequest={handleSendRequest}
        onUndoFriendship={handleUndoFriendship}
      />
      <PostCreationForm onCreatePost={handleCreatePost} show={isMyself} />
      <Timeline
        posts={posts}
        onLike={handleLike}
        myself={myself}
        onUpdateVisibility={handleUpdateVisibility}
        onCreateComment={handleCreateComment}
      />
    </LayoutContainer>
  );
}
