package org.likelion.likelionassignmentcrud.users.application;

import org.likelion.likelionassignmentcrud.users.api.dto.request.UsersSaveReqDto;
import org.likelion.likelionassignmentcrud.users.api.dto.request.UsersUpdateReqDto;
import org.likelion.likelionassignmentcrud.users.api.dto.response.UsersInfoResDto;
import org.likelion.likelionassignmentcrud.users.api.dto.response.UsersListResDto;
import org.likelion.likelionassignmentcrud.users.domain.Users;
import org.likelion.likelionassignmentcrud.users.domain.repository.UsersRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class UsersService {
    private final UsersRepository usersRepository;

    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    // Create
    @Transactional
    public void usersSave(UsersSaveReqDto usersSaveReqDto) {
        Users users = Users.builder()
                .name(usersSaveReqDto.name())
                .phoneNumber(usersSaveReqDto.phoneNumber())
                .option(usersSaveReqDto.option())
                .build();

        usersRepository.save(users);
    }

    public UsersListResDto usersFindAll() {
        List<Users> usersList = usersRepository.findAll();

        List<UsersInfoResDto> usersInfoResDtoList = usersList.stream()
                .map(UsersInfoResDto::from)
                .toList();
            return  UsersListResDto.from(usersInfoResDtoList);
    }

    public UsersInfoResDto usersFindOne(Long usersId) {
        Users users = usersRepository.findById(usersId).orElseThrow(IllegalArgumentException::new);

        return UsersInfoResDto.from(users);
    }

    //Update
    @Transactional
    public void usersUpdate(Long usersId, UsersUpdateReqDto usersUpdateReqDto) {
        Users users = usersRepository.findById(usersId).orElseThrow(IllegalArgumentException::new);

        users.update(usersUpdateReqDto);
    }

    //Delete
    @Transactional
    public void usersDelete(Long usersId) {
        Users users = usersRepository.findById(usersId).orElseThrow(IllegalArgumentException::new);

        usersRepository.delete(users);
    }

}
