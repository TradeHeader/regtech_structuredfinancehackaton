package cdm.observable.asset.meta;

import cdm.observable.asset.BondChoiceModel;
import cdm.observable.asset.validation.BondChoiceModelTypeFormatValidator;
import cdm.observable.asset.validation.BondChoiceModelValidator;
import cdm.observable.asset.validation.exists.BondChoiceModelOnlyExistsValidator;
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
@RosettaMeta(model=BondChoiceModel.class)
public class BondChoiceModelMeta implements RosettaMetaData<BondChoiceModel> {

	@Override
	public List<Validator<? super BondChoiceModel>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
			factory.create(cdm.observable.asset.validation.datarule.BondChoiceModelOneOf0.class)
		);
	}
	
	@Override
	public List<Function<? super BondChoiceModel, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super BondChoiceModel> validator() {
		return new BondChoiceModelValidator();
	}

	@Override
	public Validator<? super BondChoiceModel> typeFormatValidator() {
		return new BondChoiceModelTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super BondChoiceModel, Set<String>> onlyExistsValidator() {
		return new BondChoiceModelOnlyExistsValidator();
	}
}
