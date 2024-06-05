package cdm.product.asset.meta;

import cdm.product.asset.BondReference;
import cdm.product.asset.validation.BondReferenceTypeFormatValidator;
import cdm.product.asset.validation.BondReferenceValidator;
import cdm.product.asset.validation.exists.BondReferenceOnlyExistsValidator;
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
@RosettaMeta(model=BondReference.class)
public class BondReferenceMeta implements RosettaMetaData<BondReference> {

	@Override
	public List<Validator<? super BondReference>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super BondReference, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super BondReference> validator() {
		return new BondReferenceValidator();
	}

	@Override
	public Validator<? super BondReference> typeFormatValidator() {
		return new BondReferenceTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super BondReference, Set<String>> onlyExistsValidator() {
		return new BondReferenceOnlyExistsValidator();
	}
}
