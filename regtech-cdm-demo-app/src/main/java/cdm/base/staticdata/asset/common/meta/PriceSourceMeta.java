package cdm.base.staticdata.asset.common.meta;

import cdm.base.staticdata.asset.common.PriceSource;
import cdm.base.staticdata.asset.common.validation.PriceSourceTypeFormatValidator;
import cdm.base.staticdata.asset.common.validation.PriceSourceValidator;
import cdm.base.staticdata.asset.common.validation.exists.PriceSourceOnlyExistsValidator;
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
@RosettaMeta(model=PriceSource.class)
public class PriceSourceMeta implements RosettaMetaData<PriceSource> {

	@Override
	public List<Validator<? super PriceSource>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
			factory.create(cdm.base.staticdata.asset.common.validation.datarule.PriceSourcePriceSourceHeading.class)
		);
	}
	
	@Override
	public List<Function<? super PriceSource, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super PriceSource> validator() {
		return new PriceSourceValidator();
	}

	@Override
	public Validator<? super PriceSource> typeFormatValidator() {
		return new PriceSourceTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super PriceSource, Set<String>> onlyExistsValidator() {
		return new PriceSourceOnlyExistsValidator();
	}
}
