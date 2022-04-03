import React, { useEffect, useState } from 'react';
import { useForm } from 'react-hook-form';
import { useHistory } from 'react-router-dom';
import { useUserApi } from '../../../hooks';
import {
  Alert,
  Button,
  FloatingBox,
  InputText,
  LayoutContainer,
} from '../../components';

export function ProfileEditionScreen() {
  const { goBack } = useHistory();
  const { update, findMyself } = useUserApi();
  const [responseError, setResponseError] = useState(null);
  const { register, handleSubmit, errors, reset, setValue } = useForm();

  useEffect(() => {
    (async () => {
      const { name, nickname, profilePhoto } = await findMyself();

      setValue('name', name);
      setValue('nickname', nickname);
      setValue('profilePhoto', profilePhoto);
    })();
  }, []);

  const clearResponseError = () => setResponseError(null);

  const handleProfileEdition = async ({ name, nickname, profilePhoto }) => {
    clearResponseError();

    const response = await update(name, nickname, profilePhoto);

    if (response?.error) setResponseError(response.error);
    else goBack();
  };

  return (
    <>
      <LayoutContainer centerColumn>
        <FloatingBox minWidth={300}>
          <h1>Profile Edition</h1>
          <form onSubmit={handleSubmit(handleProfileEdition)}>
            {responseError && <Alert error>{responseError}</Alert>}

            <InputText
              register={register({ required: true })}
              name="name"
              label="Name"
              placeholder="Jonas Blohcass"
              hasError={errors.name}
              errorMessage="Name cannot be empty"
            />

            <InputText
              register={register({ maxLength: 50 })}
              name="nickname"
              label="Nickname"
              placeholder="Joninhas"
              hasError={errors.nickname}
              errorMessage="Nickname must have at least 3 and at most 50 characters"
            />

            <InputText
              register={register({ maxLength: 512 })}
              name="profilePhoto"
              label="Profile photo URL"
              hasError={errors.profilePhoto}
              errorMessage="Profile photo must have at most 512 characters"
            />

            <Button>Save changes</Button>
          </form>
        </FloatingBox>
      </LayoutContainer>
    </>
  );
}
