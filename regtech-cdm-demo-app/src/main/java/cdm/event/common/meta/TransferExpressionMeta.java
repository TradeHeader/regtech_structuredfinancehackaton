package cdm.event.common.meta;

import cdm.event.common.TransferExpression;
import cdm.event.common.validation.TransferExpressionTypeFormatValidator;
import cdm.event.common.validation.TransferExpressionValidator;
import cdm.event.common.validation.exists.TransferExpressionOnlyExistsValidator;
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
@RosettaMeta(model=TransferExpression.class)
public class TransferExpressionMeta implements RosettaMetaData<TransferExpression> {

	@Override
	public List<Validator<? super TransferExpression>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
			factory.create(cdm.event.common.validation.datarule.TransferExpressionOneOf0.class)
		);
	}
	
	@Override
	public List<Function<? super TransferExpression, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super TransferExpression> validator() {
		return new TransferExpressionValidator();
	}

	@Override
	public Validator<? super TransferExpression> typeFormatValidator() {
		return new TransferExpressionTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super TransferExpression, Set<String>> onlyExistsValidator() {
		return new TransferExpressionOnlyExistsValidator();
	}
}
