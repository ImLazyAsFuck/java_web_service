package com.ss16.service.staff;

import com.ss16.model.dto.StaffCreateDTO;
import com.ss16.model.dto.StaffDTO;
import com.ss16.model.dto.StaffUpdateDTO;
import com.ss16.model.entity.Staff;
import com.ss16.model.enums.ERole;
import com.ss16.model.enums.StaffStatus;
import com.ss16.repository.StaffRepository;
import com.ss16.security.AuthenticationFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StaffServiceImpl implements StaffService {

    private final StaffRepository staffRepository;
    private final AuthenticationFacade authenticationFacade;

    @Override
    public StaffDTO createStaff(StaffCreateDTO dto) {
        if (staffRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new RuntimeException("Email đã tồn tại");
        }

        Staff staff = Staff.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .role(ERole.ROLE_STAFF)
                .status(StaffStatus.ACTIVE)
                .build();

        return toDTO(staffRepository.save(staff));
    }

    @Override
    public StaffDTO updateMyInfo(StaffUpdateDTO dto) {
        Staff staff = getCurrentStaff();
        staff.setName(dto.getName());
        staff.setPhone(dto.getPhone());
        return toDTO(staffRepository.save(staff));
    }

    @Override
    public StaffDTO getMyInfo() {
        return toDTO(getCurrentStaff());
    }

    @Override
    public StaffDTO updateRole(Long id, ERole newRole) {
        Staff staff = staffRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy nhân viên"));
        staff.setRole(newRole);
        return toDTO(staffRepository.save(staff));
    }

    private Staff getCurrentStaff() {
        String email = authenticationFacade.getCurrentUserEmail();
        return staffRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy nhân viên hiện tại"));
    }

    private StaffDTO toDTO(Staff s) {
        return StaffDTO.builder()
                .id(s.getId())
                .name(s.getName())
                .email(s.getEmail())
                .phone(s.getPhone())
                .role(s.getRole())
                .status(s.getStatus())
                .build();
    }
}
