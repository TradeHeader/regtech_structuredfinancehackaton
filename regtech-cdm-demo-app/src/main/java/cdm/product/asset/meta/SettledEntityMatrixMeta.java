package cdm.product.asset.meta;

import cdm.product.asset.SettledEntityMatrix;
import cdm.product.asset.validation.SettledEntityMatrixTypeFormatValidator;
import cdm.product.asset.validation.SettledEntityMatrixValidator;
import cdm.product.asset.validation.exists.SettledEntityMatrixOnlyExistsValidator;
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
@RosettaMeta(model=SettledEntityMatrix.class)
public class SettledEntityMatrixMeta implements RosettaMetaData<SettledEntityMatrix> {

	@Override
	public List<Validator<? super SettledEntityMatrix>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super SettledEntityMatrix, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super SettledEntityMatrix> validator() {
		return new SettledEntityMatrixValidator();
	}

	@Override
	public Validator<? super SettledEntityMatrix> typeFormatValidator() {
		return new SettledEntityMatrixTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super SettledEntityMatrix, Set<String>> onlyExistsValidator() {
		return new SettledEntityMatrixOnlyExistsValidator();
	}
}
