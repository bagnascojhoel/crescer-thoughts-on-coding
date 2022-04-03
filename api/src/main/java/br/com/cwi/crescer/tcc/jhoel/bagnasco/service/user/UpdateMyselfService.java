package br.com.cwi.crescer.tcc.jhoel.bagnasco.service.user;

import br.com.cwi.crescer.tcc.jhoel.bagnasco.controller.request.PartialUpdateUserRequest;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.domain.User;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateMyselfService {

    @Autowired
    private FindDomainMyselfService findDomainMyselfService;

    @Autowired
    private UserRepository userRepository;

    public void update(PartialUpdateUserRequest request) {
        User user = findDomainMyselfService.find();

        user.setName(request.getName());
        user.setNickname(request.getNickname());
        user.setProfilePhoto(request.getProfilePhoto());

        userRepository.save(user);
    }
}
