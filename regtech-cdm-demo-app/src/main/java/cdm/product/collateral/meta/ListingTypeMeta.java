package cdm.product.collateral.meta;

import cdm.product.collateral.ListingType;
import cdm.product.collateral.validation.ListingTypeTypeFormatValidator;
import cdm.product.collateral.validation.ListingTypeValidator;
import cdm.product.collateral.validation.exists.ListingTypeOnlyExistsValidator;
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
@RosettaMeta(model=ListingType.class)
public class ListingTypeMeta implements RosettaMetaData<ListingType> {

	@Override
	public List<Validator<? super ListingType>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super ListingType, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super ListingType> validator() {
		return new ListingTypeValidator();
	}

	@Override
	public Validator<? super ListingType> typeFormatValidator() {
		return new ListingTypeTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super ListingType, Set<String>> onlyExistsValidator() {
		return new ListingTypeOnlyExistsValidator();
	}
}
