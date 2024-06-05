package cdm.product.collateral.meta;

import cdm.product.collateral.EligibilityQuery;
import cdm.product.collateral.validation.EligibilityQueryTypeFormatValidator;
import cdm.product.collateral.validation.EligibilityQueryValidator;
import cdm.product.collateral.validation.exists.EligibilityQueryOnlyExistsValidator;
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
@RosettaMeta(model=EligibilityQuery.class)
public class EligibilityQueryMeta implements RosettaMetaData<EligibilityQuery> {

	@Override
	public List<Validator<? super EligibilityQuery>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super EligibilityQuery, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super EligibilityQuery> validator() {
		return new EligibilityQueryValidator();
	}

	@Override
	public Validator<? super EligibilityQuery> typeFormatValidator() {
		return new EligibilityQueryTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super EligibilityQuery, Set<String>> onlyExistsValidator() {
		return new EligibilityQueryOnlyExistsValidator();
	}
}
