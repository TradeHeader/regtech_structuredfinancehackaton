package cdm.base.staticdata.asset.common.meta;

import cdm.base.staticdata.asset.common.TaxonomyClassification;
import cdm.base.staticdata.asset.common.validation.TaxonomyClassificationTypeFormatValidator;
import cdm.base.staticdata.asset.common.validation.TaxonomyClassificationValidator;
import cdm.base.staticdata.asset.common.validation.exists.TaxonomyClassificationOnlyExistsValidator;
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
@RosettaMeta(model=TaxonomyClassification.class)
public class TaxonomyClassificationMeta implements RosettaMetaData<TaxonomyClassification> {

	@Override
	public List<Validator<? super TaxonomyClassification>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super TaxonomyClassification, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super TaxonomyClassification> validator() {
		return new TaxonomyClassificationValidator();
	}

	@Override
	public Validator<? super TaxonomyClassification> typeFormatValidator() {
		return new TaxonomyClassificationTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super TaxonomyClassification, Set<String>> onlyExistsValidator() {
		return new TaxonomyClassificationOnlyExistsValidator();
	}
}
