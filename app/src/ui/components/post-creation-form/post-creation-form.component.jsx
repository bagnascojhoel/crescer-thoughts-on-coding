import './styles.css';
import React, { useState } from 'react';
import { useForm } from 'react-hook-form';
import { FloatingBox, InputSelect, InputTextArea, Alert, Button } from '..';
import { POST_VISIBILITY, usePostApi } from '../../../hooks';

export function PostCreationForm({ onCreatePost, show }) {
  const { createPost } = usePostApi();
  const [responseError, setResponseError] = useState(null);
  const { register, handleSubmit, errors, reset } = useForm();

  const handlePostCreation = async (data) => {
    const { error } = await createPost(data);

    if (error) {
      setResponseError(error);
    } else {
      reset();
      onCreatePost();
    }
  };

  return (
    show ? (
      <FloatingBox className="post-form">
        <form onSubmit={handleSubmit(handlePostCreation)}>
          {responseError && <Alert error>{responseError}</Alert>}

          <InputTextArea
            register={register({ required: true })}
            name="content"
            label="Post"
            placeholder="Have you ever noticed that..."
            hasError={errors.content}
            errorMessage="Post content cannot be empty"
          />

          <InputSelect
            register={register({ required: true })}
            hasError={errors.visibility}
            errorMessage="Visibility cannot be empty"
            label="Visibility"
            name="visibility"
            options={mapVisibilityOptions()}
          />
          <Button>Post</Button>
        </form>
      </FloatingBox>
    ): <></>
  );
}

function mapVisibilityOptions() {
  return Object.entries(POST_VISIBILITY).map(([value, text]) => ({
    value,
    text,
  }));
}
