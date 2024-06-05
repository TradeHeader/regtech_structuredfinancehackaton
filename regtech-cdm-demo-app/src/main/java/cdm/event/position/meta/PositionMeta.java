package cdm.event.position.meta;

import cdm.event.position.Position;
import cdm.event.position.validation.PositionTypeFormatValidator;
import cdm.event.position.validation.PositionValidator;
import cdm.event.position.validation.exists.PositionOnlyExistsValidator;
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
@RosettaMeta(model=Position.class)
public class PositionMeta implements RosettaMetaData<Position> {

	@Override
	public List<Validator<? super Position>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super Position, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super Position> validator() {
		return new PositionValidator();
	}

	@Override
	public Validator<? super Position> typeFormatValidator() {
		return new PositionTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super Position, Set<String>> onlyExistsValidator() {
		return new PositionOnlyExistsValidator();
	}
}
