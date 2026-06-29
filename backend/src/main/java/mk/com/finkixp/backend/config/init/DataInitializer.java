package mk.com.finkixp.backend.config.init;

import jakarta.annotation.PostConstruct;
import mk.com.finkixp.backend.model.domain.Subject;
import mk.com.finkixp.backend.model.domain.Task;
import mk.com.finkixp.backend.model.domain.User;
import mk.com.finkixp.backend.model.enums.Difficulty;
import mk.com.finkixp.backend.model.enums.Role;
import mk.com.finkixp.backend.repository.SubjectRepository;
import mk.com.finkixp.backend.repository.TaskRepository;
import mk.com.finkixp.backend.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataInitializer {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final SubjectRepository subjectRepository;
    private final TaskRepository taskRepository;

    public DataInitializer(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder, SubjectRepository subjectRepository, TaskRepository taskRepository
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.subjectRepository = subjectRepository;
        this.taskRepository = taskRepository;
    }

    @PostConstruct
    public void init() {
        User admin = createUserIfMissing(new User(
                "admin",
                passwordEncoder.encode("admin"),
                "Admin",
                "Admin",
                Role.ROLE_ADMIN
        ));

        createUserIfMissing(new User(
                "john",
                passwordEncoder.encode("john"),
                "John",
                "Test",
                Role.ROLE_MECHANIC
        ));

        createUserIfMissing(new User(
                "user",
                passwordEncoder.encode("user"),
                "User",
                "User",
                Role.ROLE_USER
        ));

        if (subjectRepository.count() > 0 || taskRepository.count() > 0) {
            return;
        }

        Subject oop = new Subject("ООП");
        Subject aok = new Subject("АОК");
        Subject sp = new Subject("СП");
        Subject os = new Subject("ОС");
        Subject bnp = new Subject("БнП");
        Subject vp = new Subject("ВП");
        Subject nvd = new Subject("НвД");
        Subject dnick = new Subject("ДНИЧК");
        Subject emt = new Subject("ЕМТ");

        subjectRepository.saveAll(List.of(oop, aok, sp, os, bnp, vp, nvd, dnick, emt));

        Task oopTask = new Task(
                "Наследување во Java",
                "Напиши пример за наследување и полиморфизам во ООП",
                oop,
                admin,
                Difficulty.MEDIUM

        );

        Task aokTask = new Task(
                "Вежби за машински јазик",
                "Реши 5 задачи за адресирање и инструкции",
                aok,
                admin,
                Difficulty.EASY

        );

        Task spTask = new Task(
                "Процеси и нишки",
                "Направи кратка презентација за процеси и нитки во СП",
                sp,
                admin,
                Difficulty.EASY

        );

        Task osTask = new Task(
                "Вовед во Linux",
                "Инсталирај виртуелна машина со Ubuntu и пробај 10 основни команди",
                os,
                admin,
                Difficulty.MEDIUM
        );

        Task bnpTask = new Task(
                "Финансиска анализа",
                "Реши задача за биланс на состојба од минат испит",
                bnp,
                admin,
                Difficulty.HARD
        );

        Task vpTask = new Task(
                "Генератор на слики",
                "Креирај мал проект во OpenCV за препознавање форми",
                vp,
                admin,
                Difficulty.HARD
        );

        Task nvdTask = new Task(
                "Мини вежба со WebGL",
                "Пробај едноставен пример со WebGL во браузер",
                nvd,
                admin,
                Difficulty.HARD
        );

        Task dnickTask = new Task(
                "Алгоритам за распоредување",
                "Имплементирај Round-Robin распоредувач",
                dnick,
                admin,
                Difficulty.EASY

        );

        Task emtTask = new Task(
                "Тестирање на аналогно коло",
                "Симулирај RC филтер во Multisim",
                emt,
                admin,
                Difficulty.MEDIUM
        );

        taskRepository.saveAll(List.of(oopTask, aokTask, spTask, osTask, bnpTask, vpTask, nvdTask, dnickTask, emtTask));
    }

    private User createUserIfMissing(User user) {
        return userRepository.findByUsername(user.getUsername())
                .orElseGet(() -> userRepository.save(user));
    }

}
