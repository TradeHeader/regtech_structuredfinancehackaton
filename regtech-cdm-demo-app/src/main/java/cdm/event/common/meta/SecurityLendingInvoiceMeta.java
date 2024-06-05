package cdm.event.common.meta;

import cdm.event.common.SecurityLendingInvoice;
import cdm.event.common.validation.SecurityLendingInvoiceTypeFormatValidator;
import cdm.event.common.validation.SecurityLendingInvoiceValidator;
import cdm.event.common.validation.exists.SecurityLendingInvoiceOnlyExistsValidator;
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
@RosettaMeta(model=SecurityLendingInvoice.class)
public class SecurityLendingInvoiceMeta implements RosettaMetaData<SecurityLendingInvoice> {

	@Override
	public List<Validator<? super SecurityLendingInvoice>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super SecurityLendingInvoice, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super SecurityLendingInvoice> validator() {
		return new SecurityLendingInvoiceValidator();
	}

	@Override
	public Validator<? super SecurityLendingInvoice> typeFormatValidator() {
		return new SecurityLendingInvoiceTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super SecurityLendingInvoice, Set<String>> onlyExistsValidator() {
		return new SecurityLendingInvoiceOnlyExistsValidator();
	}
}
