package cdm.event.workflow.meta;

import cdm.event.workflow.Velocity;
import cdm.event.workflow.validation.VelocityTypeFormatValidator;
import cdm.event.workflow.validation.VelocityValidator;
import cdm.event.workflow.validation.exists.VelocityOnlyExistsValidator;
import com.rosetta.model.lib.annotations.RosettaMeta;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.qualify.QualifyFunctionFactory;
import com.rosetta.model.lib.qualify.QualifyResult;
import com.rosetta.model.lib.validation.Validator;
import com.rosetta.model.lib.validation.ValidatorFactory;
import com.rosetta.model.lib.validation.ValidatorWithArg;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.function.Function;


/**
 * @version ${project.version}
 */
@RosettaMeta(model=Velocity.class)
public class VelocityMeta implements RosettaMetaData<Velocity> {

	@Override
	public List<Validator<? super Velocity>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super Velocity, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super Velocity> validator() {
		return new VelocityValidator();
	}

	@Override
	public Validator<? super Velocity> typeFormatValidator() {
		return new VelocityTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super Velocity, Set<String>> onlyExistsValidator() {
		return new VelocityOnlyExistsValidator();
	}
}
