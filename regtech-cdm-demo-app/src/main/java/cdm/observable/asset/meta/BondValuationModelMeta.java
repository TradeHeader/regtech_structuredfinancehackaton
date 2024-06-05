package cdm.observable.asset.meta;

import cdm.observable.asset.BondValuationModel;
import cdm.observable.asset.validation.BondValuationModelTypeFormatValidator;
import cdm.observable.asset.validation.BondValuationModelValidator;
import cdm.observable.asset.validation.exists.BondValuationModelOnlyExistsValidator;
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
@RosettaMeta(model=BondValuationModel.class)
public class BondValuationModelMeta implements RosettaMetaData<BondValuationModel> {

	@Override
	public List<Validator<? super BondValuationModel>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super BondValuationModel, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super BondValuationModel> validator() {
		return new BondValuationModelValidator();
	}

	@Override
	public Validator<? super BondValuationModel> typeFormatValidator() {
		return new BondValuationModelTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super BondValuationModel, Set<String>> onlyExistsValidator() {
		return new BondValuationModelOnlyExistsValidator();
	}
}
