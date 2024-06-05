package cdm.observable.asset.meta;

import cdm.observable.asset.FallbackReferencePrice;
import cdm.observable.asset.validation.FallbackReferencePriceTypeFormatValidator;
import cdm.observable.asset.validation.FallbackReferencePriceValidator;
import cdm.observable.asset.validation.exists.FallbackReferencePriceOnlyExistsValidator;
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
@RosettaMeta(model=FallbackReferencePrice.class)
public class FallbackReferencePriceMeta implements RosettaMetaData<FallbackReferencePrice> {

	@Override
	public List<Validator<? super FallbackReferencePrice>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
			factory.create(cdm.observable.asset.validation.datarule.FallbackReferencePriceMaximumDaysOfPostponement.class),
			factory.create(cdm.observable.asset.validation.datarule.FallbackReferencePriceFallbackCalculationAgent.class)
		);
	}
	
	@Override
	public List<Function<? super FallbackReferencePrice, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super FallbackReferencePrice> validator() {
		return new FallbackReferencePriceValidator();
	}

	@Override
	public Validator<? super FallbackReferencePrice> typeFormatValidator() {
		return new FallbackReferencePriceTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super FallbackReferencePrice, Set<String>> onlyExistsValidator() {
		return new FallbackReferencePriceOnlyExistsValidator();
	}
}
