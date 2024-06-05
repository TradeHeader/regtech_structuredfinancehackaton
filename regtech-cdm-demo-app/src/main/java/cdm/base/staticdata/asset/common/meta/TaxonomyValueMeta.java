package cdm.base.staticdata.asset.common.meta;

import cdm.base.staticdata.asset.common.TaxonomyValue;
import cdm.base.staticdata.asset.common.validation.TaxonomyValueTypeFormatValidator;
import cdm.base.staticdata.asset.common.validation.TaxonomyValueValidator;
import cdm.base.staticdata.asset.common.validation.exists.TaxonomyValueOnlyExistsValidator;
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
@RosettaMeta(model=TaxonomyValue.class)
public class TaxonomyValueMeta implements RosettaMetaData<TaxonomyValue> {

	@Override
	public List<Validator<? super TaxonomyValue>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
			factory.create(cdm.base.staticdata.asset.common.validation.datarule.TaxonomyValueValueExists.class)
		);
	}
	
	@Override
	public List<Function<? super TaxonomyValue, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super TaxonomyValue> validator() {
		return new TaxonomyValueValidator();
	}

	@Override
	public Validator<? super TaxonomyValue> typeFormatValidator() {
		return new TaxonomyValueTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super TaxonomyValue, Set<String>> onlyExistsValidator() {
		return new TaxonomyValueOnlyExistsValidator();
	}
}
