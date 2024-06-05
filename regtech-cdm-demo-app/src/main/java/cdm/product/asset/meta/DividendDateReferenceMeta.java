package cdm.product.asset.meta;

import cdm.product.asset.DividendDateReference;
import cdm.product.asset.validation.DividendDateReferenceTypeFormatValidator;
import cdm.product.asset.validation.DividendDateReferenceValidator;
import cdm.product.asset.validation.exists.DividendDateReferenceOnlyExistsValidator;
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
@RosettaMeta(model=DividendDateReference.class)
public class DividendDateReferenceMeta implements RosettaMetaData<DividendDateReference> {

	@Override
	public List<Validator<? super DividendDateReference>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
			factory.create(cdm.product.asset.validation.datarule.DividendDateReferencePaymentDateOffset.class)
		);
	}
	
	@Override
	public List<Function<? super DividendDateReference, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super DividendDateReference> validator() {
		return new DividendDateReferenceValidator();
	}

	@Override
	public Validator<? super DividendDateReference> typeFormatValidator() {
		return new DividendDateReferenceTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super DividendDateReference, Set<String>> onlyExistsValidator() {
		return new DividendDateReferenceOnlyExistsValidator();
	}
}
