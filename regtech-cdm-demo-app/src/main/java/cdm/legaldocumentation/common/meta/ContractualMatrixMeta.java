package cdm.legaldocumentation.common.meta;

import cdm.legaldocumentation.common.ContractualMatrix;
import cdm.legaldocumentation.common.validation.ContractualMatrixTypeFormatValidator;
import cdm.legaldocumentation.common.validation.ContractualMatrixValidator;
import cdm.legaldocumentation.common.validation.exists.ContractualMatrixOnlyExistsValidator;
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
@RosettaMeta(model=ContractualMatrix.class)
public class ContractualMatrixMeta implements RosettaMetaData<ContractualMatrix> {

	@Override
	public List<Validator<? super ContractualMatrix>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super ContractualMatrix, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super ContractualMatrix> validator() {
		return new ContractualMatrixValidator();
	}

	@Override
	public Validator<? super ContractualMatrix> typeFormatValidator() {
		return new ContractualMatrixTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super ContractualMatrix, Set<String>> onlyExistsValidator() {
		return new ContractualMatrixOnlyExistsValidator();
	}
}
