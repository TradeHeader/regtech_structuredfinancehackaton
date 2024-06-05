package cdm.observable.asset.meta;

import cdm.observable.asset.MultipleDebtTypes;
import cdm.observable.asset.validation.MultipleDebtTypesTypeFormatValidator;
import cdm.observable.asset.validation.MultipleDebtTypesValidator;
import cdm.observable.asset.validation.exists.MultipleDebtTypesOnlyExistsValidator;
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
@RosettaMeta(model=MultipleDebtTypes.class)
public class MultipleDebtTypesMeta implements RosettaMetaData<MultipleDebtTypes> {

	@Override
	public List<Validator<? super MultipleDebtTypes>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super MultipleDebtTypes, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super MultipleDebtTypes> validator() {
		return new MultipleDebtTypesValidator();
	}

	@Override
	public Validator<? super MultipleDebtTypes> typeFormatValidator() {
		return new MultipleDebtTypesTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super MultipleDebtTypes, Set<String>> onlyExistsValidator() {
		return new MultipleDebtTypesOnlyExistsValidator();
	}
}
