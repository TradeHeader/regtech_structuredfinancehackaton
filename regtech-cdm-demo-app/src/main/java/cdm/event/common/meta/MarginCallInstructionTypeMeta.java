package cdm.event.common.meta;

import cdm.event.common.MarginCallInstructionType;
import cdm.event.common.validation.MarginCallInstructionTypeTypeFormatValidator;
import cdm.event.common.validation.MarginCallInstructionTypeValidator;
import cdm.event.common.validation.exists.MarginCallInstructionTypeOnlyExistsValidator;
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
@RosettaMeta(model=MarginCallInstructionType.class)
public class MarginCallInstructionTypeMeta implements RosettaMetaData<MarginCallInstructionType> {

	@Override
	public List<Validator<? super MarginCallInstructionType>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
			factory.create(cdm.event.common.validation.datarule.MarginCallInstructionTypeCallTypeExpectedVisibility.class)
		);
	}
	
	@Override
	public List<Function<? super MarginCallInstructionType, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super MarginCallInstructionType> validator() {
		return new MarginCallInstructionTypeValidator();
	}

	@Override
	public Validator<? super MarginCallInstructionType> typeFormatValidator() {
		return new MarginCallInstructionTypeTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super MarginCallInstructionType, Set<String>> onlyExistsValidator() {
		return new MarginCallInstructionTypeOnlyExistsValidator();
	}
}
