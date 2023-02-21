package com.aws.springcloudawslambda;

import com.aws.springcloudawslambda.entity.Student;
import com.aws.springcloudawslambda.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.function.context.FunctionRegistration;
import org.springframework.cloud.function.context.FunctionType;
import org.springframework.cloud.function.context.FunctionalSpringApplication;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.GenericApplicationContext;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@SpringBootApplication
public class SpringCloudAwsLambdaApplication implements ApplicationContextInitializer<GenericApplicationContext> {

	@Autowired
	private StudentRepository studentRepository;

	@Bean
	public Function<String, List<Student>> findByName() {
		return (input)->studentRepository.studentList().stream()
				.filter(student -> student.getName().equals(input)).collect(Collectors.toList());
	}

	@Override
	public void initialize(GenericApplicationContext context) {
		context.registerBean("findByName", FunctionRegistration.class,
				() -> new FunctionRegistration<Function<String, List<Student>>> (findByName())
						.type(FunctionType.from(String.class).to(List.class).getType()));
	}

	public static void main(String[] args) {
		FunctionalSpringApplication.run(SpringCloudAwsLambdaApplication.class, args);
	}

}
