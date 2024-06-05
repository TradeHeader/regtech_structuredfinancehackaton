package cdm.observable.asset.meta;

import cdm.observable.asset.PriceComposite;
import cdm.observable.asset.validation.PriceCompositeTypeFormatValidator;
import cdm.observable.asset.validation.PriceCompositeValidator;
import cdm.observable.asset.validation.exists.PriceCompositeOnlyExistsValidator;
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
@RosettaMeta(model=PriceComposite.class)
public class PriceCompositeMeta implements RosettaMetaData<PriceComposite> {

	@Override
	public List<Validator<? super PriceComposite>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
			factory.create(cdm.observable.asset.validation.datarule.PriceCompositeArithmeticOperator.class)
		);
	}
	
	@Override
	public List<Function<? super PriceComposite, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super PriceComposite> validator() {
		return new PriceCompositeValidator();
	}

	@Override
	public Validator<? super PriceComposite> typeFormatValidator() {
		return new PriceCompositeTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super PriceComposite, Set<String>> onlyExistsValidator() {
		return new PriceCompositeOnlyExistsValidator();
	}
}
