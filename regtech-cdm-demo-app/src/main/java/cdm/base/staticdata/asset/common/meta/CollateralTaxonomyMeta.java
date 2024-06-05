package cdm.base.staticdata.asset.common.meta;

import cdm.base.staticdata.asset.common.CollateralTaxonomy;
import cdm.base.staticdata.asset.common.validation.CollateralTaxonomyTypeFormatValidator;
import cdm.base.staticdata.asset.common.validation.CollateralTaxonomyValidator;
import cdm.base.staticdata.asset.common.validation.exists.CollateralTaxonomyOnlyExistsValidator;
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
@RosettaMeta(model=CollateralTaxonomy.class)
public class CollateralTaxonomyMeta implements RosettaMetaData<CollateralTaxonomy> {

	@Override
	public List<Validator<? super CollateralTaxonomy>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
			factory.create(cdm.base.staticdata.asset.common.validation.datarule.CollateralTaxonomyEuEligibleCollateralTaxonomy.class),
			factory.create(cdm.base.staticdata.asset.common.validation.datarule.CollateralTaxonomyUkEligibleCollateralTaxonomy.class),
			factory.create(cdm.base.staticdata.asset.common.validation.datarule.CollateralTaxonomyUsEligibleCollateralTaxonomy.class),
			factory.create(cdm.base.staticdata.asset.common.validation.datarule.CollateralTaxonomyTaxonomyValue.class)
		);
	}
	
	@Override
	public List<Function<? super CollateralTaxonomy, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super CollateralTaxonomy> validator() {
		return new CollateralTaxonomyValidator();
	}

	@Override
	public Validator<? super CollateralTaxonomy> typeFormatValidator() {
		return new CollateralTaxonomyTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super CollateralTaxonomy, Set<String>> onlyExistsValidator() {
		return new CollateralTaxonomyOnlyExistsValidator();
	}
}
