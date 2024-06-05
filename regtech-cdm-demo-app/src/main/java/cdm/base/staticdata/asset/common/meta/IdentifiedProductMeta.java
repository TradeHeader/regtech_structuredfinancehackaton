package cdm.base.staticdata.asset.common.meta;

import cdm.base.staticdata.asset.common.IdentifiedProduct;
import cdm.base.staticdata.asset.common.validation.IdentifiedProductTypeFormatValidator;
import cdm.base.staticdata.asset.common.validation.IdentifiedProductValidator;
import cdm.base.staticdata.asset.common.validation.exists.IdentifiedProductOnlyExistsValidator;
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
@RosettaMeta(model=IdentifiedProduct.class)
public class IdentifiedProductMeta implements RosettaMetaData<IdentifiedProduct> {

	@Override
	public List<Validator<? super IdentifiedProduct>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super IdentifiedProduct, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super IdentifiedProduct> validator() {
		return new IdentifiedProductValidator();
	}

	@Override
	public Validator<? super IdentifiedProduct> typeFormatValidator() {
		return new IdentifiedProductTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super IdentifiedProduct, Set<String>> onlyExistsValidator() {
		return new IdentifiedProductOnlyExistsValidator();
	}
}
