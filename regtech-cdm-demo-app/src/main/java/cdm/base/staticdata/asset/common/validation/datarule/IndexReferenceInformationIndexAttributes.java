package cdm.base.staticdata.asset.common.validation.datarule;

import cdm.base.staticdata.asset.common.IndexReferenceInformation;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.annotations.RosettaDataRule;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.mapper.MapperS;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.Validator;
import com.rosetta.model.metafields.FieldWithMetaString;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

/**
 * @version ${project.version}
 */
@RosettaDataRule("IndexReferenceInformationIndexAttributes")
@ImplementedBy(IndexReferenceInformationIndexAttributes.Default.class)
public interface IndexReferenceInformationIndexAttributes extends Validator<IndexReferenceInformation> {
	
	String NAME = "IndexReferenceInformationIndexAttributes";
	String DEFINITION = "indexName exists or indexId exists";
	
	ValidationResult<IndexReferenceInformation> validate(RosettaPath path, IndexReferenceInformation indexReferenceInformation);
	
	class Default implements IndexReferenceInformationIndexAttributes {
	
		@Override
		public ValidationResult<IndexReferenceInformation> validate(RosettaPath path, IndexReferenceInformation indexReferenceInformation) {
			ComparisonResult result = executeDataRule(indexReferenceInformation);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "IndexReferenceInformation", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "IndexReferenceInformation", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(IndexReferenceInformation indexReferenceInformation) {
			try {
				return exists(MapperS.of(indexReferenceInformation).<FieldWithMetaString>map("getIndexName", _indexReferenceInformation -> _indexReferenceInformation.getIndexName()).<String>map("getValue", _f->_f.getValue())).or(exists(MapperS.of(indexReferenceInformation).<FieldWithMetaString>mapC("getIndexId", _indexReferenceInformation -> _indexReferenceInformation.getIndexId()).<String>map("getValue", _f->_f.getValue())));
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements IndexReferenceInformationIndexAttributes {
	
		@Override
		public ValidationResult<IndexReferenceInformation> validate(RosettaPath path, IndexReferenceInformation indexReferenceInformation) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "IndexReferenceInformation", path, DEFINITION);
		}
	}
}
