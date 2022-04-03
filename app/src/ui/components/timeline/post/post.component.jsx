import './styles.css';
import React, { useState } from 'react';
import { AiFillHeart, AiOutlineHeart } from 'react-icons/ai';
import { SquareButton, Button } from '../../';
import { POST_VISIBILITY } from '../../../../hooks';
import { MdMessage } from 'react-icons/md';
import { useForm } from 'react-hook-form';
import { InputTextArea } from '../../input-text-area/input-text-area.component';
import { DataList } from '../../data-list/data-list.component';

const iconSize = 30;

function onUpdateVisibilityText({ visibility }) {
  return visibility === POST_VISIBILITY.PRIVATE
    ? POST_VISIBILITY.PUBLIC
    : POST_VISIBILITY.PRIVATE;
}

export function Post({
  data,
  onLike,
  myself,
  onUpdateVisibility,
  onCreateComment,
}) {
  const [showCommentForm, setShowCommentForm] = useState(false);
  const {
    id,
    authorNickname,
    authorName,
    authorEmail,
    content,
    hasMyLike,
    comments,
  } = data;
  const canUpdate = authorEmail === myself.email;
  const _onLike = () => onLike(data);
  const _onUpdateVisibility = () => onUpdateVisibility(data);
  const handleComment = () => setShowCommentForm(!showCommentForm);

  return (
    <div className>
      {canUpdate && (
        <Button onClick={_onUpdateVisibility}>
          {`MAKE POST ${onUpdateVisibilityText(data)}`}
        </Button>
      )}
      <div>
        <div>
          <p className="post__content">{content}</p>
          <small className="post__author">
            By {authorNickname || authorName}
          </small>
        </div>
        <div className="post__actions">
          <SquareButton
            onClick={_onLike}
            icon={hasMyLike ? AiFillHeart : AiOutlineHeart}
            iconSize={iconSize}
          />
          <SquareButton
            icon={MdMessage}
            iconSize={iconSize}
            onClick={handleComment}
          />
        </div>
        <CommentForm
          show={showCommentForm}
          onCreateComment={onCreateComment}
          postId={id}
        />
      </div>
      <DataList
        dataList={comments}
        itemComponent={Comment}
        noDataMessage=""
        keyExtractor={({ id }) => id}
      />
    </div>
  );
}

Post.defaultProps = {
  onCreateComment: () => {},
};

function CommentForm({ postId, show, onCreateComment }) {
  const { errors, register, handleSubmit, reset } = useForm();

  const _onCreateComment = ({ content }) => {
    onCreateComment({ visibility: POST_VISIBILITY.PUBLIC, content, postId });
    reset();
  };

  return show ? (
    <form
      onSubmit={handleSubmit(_onCreateComment)}
      className="post__comment-form"
    >
      <InputTextArea
        
        register={register({ required: true })}
        name="content"
        placeholder="YOU KNOW WHAT? YOU..."
        hasError={errors.content}
        errorMessage="Comment cannot be empty"
      />
      <Button className="post__comment-form__button">Comment</Button>
    </form>
  ) : (
    <></>
  );
}

function Comment({ data: { content, authorName, authorNickname } }) {
  return (
    <div>
      <p className="post__content">{content}</p>
      <small className="post__author">By {authorNickname || authorName}</small>
    </div>
  );
}
