package cdm.base.staticdata.asset.common.meta;

import cdm.base.staticdata.asset.common.Taxonomy;
import cdm.base.staticdata.asset.common.validation.TaxonomyTypeFormatValidator;
import cdm.base.staticdata.asset.common.validation.TaxonomyValidator;
import cdm.base.staticdata.asset.common.validation.exists.TaxonomyOnlyExistsValidator;
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
@RosettaMeta(model=Taxonomy.class)
public class TaxonomyMeta implements RosettaMetaData<Taxonomy> {

	@Override
	public List<Validator<? super Taxonomy>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
			factory.create(cdm.base.staticdata.asset.common.validation.datarule.TaxonomyDifferentOrdinals.class)
		);
	}
	
	@Override
	public List<Function<? super Taxonomy, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super Taxonomy> validator() {
		return new TaxonomyValidator();
	}

	@Override
	public Validator<? super Taxonomy> typeFormatValidator() {
		return new TaxonomyTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super Taxonomy, Set<String>> onlyExistsValidator() {
		return new TaxonomyOnlyExistsValidator();
	}
}
