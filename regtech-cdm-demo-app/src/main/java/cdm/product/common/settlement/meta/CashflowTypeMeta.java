package cdm.product.common.settlement.meta;

import cdm.product.common.settlement.CashflowType;
import cdm.product.common.settlement.validation.CashflowTypeTypeFormatValidator;
import cdm.product.common.settlement.validation.CashflowTypeValidator;
import cdm.product.common.settlement.validation.exists.CashflowTypeOnlyExistsValidator;
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
@RosettaMeta(model=CashflowType.class)
public class CashflowTypeMeta implements RosettaMetaData<CashflowType> {

	@Override
	public List<Validator<? super CashflowType>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
			factory.create(cdm.product.common.settlement.validation.datarule.CashflowTypeChoice0.class)
		);
	}
	
	@Override
	public List<Function<? super CashflowType, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super CashflowType> validator() {
		return new CashflowTypeValidator();
	}

	@Override
	public Validator<? super CashflowType> typeFormatValidator() {
		return new CashflowTypeTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super CashflowType, Set<String>> onlyExistsValidator() {
		return new CashflowTypeOnlyExistsValidator();
	}
}
