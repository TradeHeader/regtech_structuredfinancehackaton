package cdm.base.staticdata.asset.common.meta;

import cdm.base.staticdata.asset.common.ProductBase;
import cdm.base.staticdata.asset.common.validation.ProductBaseTypeFormatValidator;
import cdm.base.staticdata.asset.common.validation.ProductBaseValidator;
import cdm.base.staticdata.asset.common.validation.exists.ProductBaseOnlyExistsValidator;
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
@RosettaMeta(model=ProductBase.class)
public class ProductBaseMeta implements RosettaMetaData<ProductBase> {

	@Override
	public List<Validator<? super ProductBase>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super ProductBase, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super ProductBase> validator() {
		return new ProductBaseValidator();
	}

	@Override
	public Validator<? super ProductBase> typeFormatValidator() {
		return new ProductBaseTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super ProductBase, Set<String>> onlyExistsValidator() {
		return new ProductBaseOnlyExistsValidator();
	}
}
