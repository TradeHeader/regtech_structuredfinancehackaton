package cdm.event.position.meta;

import cdm.event.position.CounterpartyPosition;
import cdm.event.position.validation.CounterpartyPositionTypeFormatValidator;
import cdm.event.position.validation.CounterpartyPositionValidator;
import cdm.event.position.validation.exists.CounterpartyPositionOnlyExistsValidator;
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
@RosettaMeta(model=CounterpartyPosition.class)
public class CounterpartyPositionMeta implements RosettaMetaData<CounterpartyPosition> {

	@Override
	public List<Validator<? super CounterpartyPosition>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super CounterpartyPosition, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super CounterpartyPosition> validator() {
		return new CounterpartyPositionValidator();
	}

	@Override
	public Validator<? super CounterpartyPosition> typeFormatValidator() {
		return new CounterpartyPositionTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super CounterpartyPosition, Set<String>> onlyExistsValidator() {
		return new CounterpartyPositionOnlyExistsValidator();
	}
}
