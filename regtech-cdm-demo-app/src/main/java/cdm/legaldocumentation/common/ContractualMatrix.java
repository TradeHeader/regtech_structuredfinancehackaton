package cdm.legaldocumentation.common;

import cdm.legaldocumentation.common.ContractualMatrix;
import cdm.legaldocumentation.common.ContractualMatrix.ContractualMatrixBuilder;
import cdm.legaldocumentation.common.ContractualMatrix.ContractualMatrixBuilderImpl;
import cdm.legaldocumentation.common.ContractualMatrix.ContractualMatrixImpl;
import cdm.legaldocumentation.common.MatrixTermEnum;
import cdm.legaldocumentation.common.MatrixTypeEnum;
import cdm.legaldocumentation.common.meta.ContractualMatrixMeta;
import cdm.legaldocumentation.common.metafields.FieldWithMetaMatrixTermEnum;
import cdm.legaldocumentation.common.metafields.FieldWithMetaMatrixTermEnum.FieldWithMetaMatrixTermEnumBuilder;
import cdm.legaldocumentation.common.metafields.FieldWithMetaMatrixTypeEnum;
import cdm.legaldocumentation.common.metafields.FieldWithMetaMatrixTypeEnum.FieldWithMetaMatrixTypeEnumBuilder;
import com.rosetta.model.lib.RosettaModelObject;
import com.rosetta.model.lib.RosettaModelObjectBuilder;
import com.rosetta.model.lib.annotations.RosettaAttribute;
import com.rosetta.model.lib.annotations.RosettaDataType;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.process.BuilderMerger;
import com.rosetta.model.lib.process.BuilderProcessor;
import com.rosetta.model.lib.process.Processor;
import java.util.Objects;

import static java.util.Optional.ofNullable;

/**
 * @version ${project.version}
 */
@RosettaDataType(value="ContractualMatrix", builder=ContractualMatrix.ContractualMatrixBuilderImpl.class, version="${project.version}")
public interface ContractualMatrix extends RosettaModelObject {

	ContractualMatrixMeta metaData = new ContractualMatrixMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Identifies the form of applicable matrix.
	 */
	FieldWithMetaMatrixTypeEnum getMatrixType();
	/**
	 * Defines any applicable key into the relevant matrix. For example, the Transaction Type would be the single term required for the Credit Derivatives Physical Settlement Matrix. This element should be omitted in the case of the 2000 ISDA Definitions Settlement Matrix for Early Termination and Swaptions.
	 */
	FieldWithMetaMatrixTermEnum getMatrixTerm();

	/*********************** Build Methods  ***********************/
	ContractualMatrix build();
	
	ContractualMatrix.ContractualMatrixBuilder toBuilder();
	
	static ContractualMatrix.ContractualMatrixBuilder builder() {
		return new ContractualMatrix.ContractualMatrixBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends ContractualMatrix> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends ContractualMatrix> getType() {
		return ContractualMatrix.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("matrixType"), processor, FieldWithMetaMatrixTypeEnum.class, getMatrixType());
		processRosetta(path.newSubPath("matrixTerm"), processor, FieldWithMetaMatrixTermEnum.class, getMatrixTerm());
	}
	

	/*********************** Builder Interface  ***********************/
	interface ContractualMatrixBuilder extends ContractualMatrix, RosettaModelObjectBuilder {
		FieldWithMetaMatrixTypeEnum.FieldWithMetaMatrixTypeEnumBuilder getOrCreateMatrixType();
		FieldWithMetaMatrixTypeEnum.FieldWithMetaMatrixTypeEnumBuilder getMatrixType();
		FieldWithMetaMatrixTermEnum.FieldWithMetaMatrixTermEnumBuilder getOrCreateMatrixTerm();
		FieldWithMetaMatrixTermEnum.FieldWithMetaMatrixTermEnumBuilder getMatrixTerm();
		ContractualMatrix.ContractualMatrixBuilder setMatrixType(FieldWithMetaMatrixTypeEnum matrixType0);
		ContractualMatrix.ContractualMatrixBuilder setMatrixTypeValue(MatrixTypeEnum matrixType1);
		ContractualMatrix.ContractualMatrixBuilder setMatrixTerm(FieldWithMetaMatrixTermEnum matrixTerm0);
		ContractualMatrix.ContractualMatrixBuilder setMatrixTermValue(MatrixTermEnum matrixTerm1);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("matrixType"), processor, FieldWithMetaMatrixTypeEnum.FieldWithMetaMatrixTypeEnumBuilder.class, getMatrixType());
			processRosetta(path.newSubPath("matrixTerm"), processor, FieldWithMetaMatrixTermEnum.FieldWithMetaMatrixTermEnumBuilder.class, getMatrixTerm());
		}
		

		ContractualMatrix.ContractualMatrixBuilder prune();
	}

	/*********************** Immutable Implementation of ContractualMatrix  ***********************/
	class ContractualMatrixImpl implements ContractualMatrix {
		private final FieldWithMetaMatrixTypeEnum matrixType;
		private final FieldWithMetaMatrixTermEnum matrixTerm;
		
		protected ContractualMatrixImpl(ContractualMatrix.ContractualMatrixBuilder builder) {
			this.matrixType = ofNullable(builder.getMatrixType()).map(f->f.build()).orElse(null);
			this.matrixTerm = ofNullable(builder.getMatrixTerm()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("matrixType")
		public FieldWithMetaMatrixTypeEnum getMatrixType() {
			return matrixType;
		}
		
		@Override
		@RosettaAttribute("matrixTerm")
		public FieldWithMetaMatrixTermEnum getMatrixTerm() {
			return matrixTerm;
		}
		
		@Override
		public ContractualMatrix build() {
			return this;
		}
		
		@Override
		public ContractualMatrix.ContractualMatrixBuilder toBuilder() {
			ContractualMatrix.ContractualMatrixBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(ContractualMatrix.ContractualMatrixBuilder builder) {
			ofNullable(getMatrixType()).ifPresent(builder::setMatrixType);
			ofNullable(getMatrixTerm()).ifPresent(builder::setMatrixTerm);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			ContractualMatrix _that = getType().cast(o);
		
			if (!Objects.equals(matrixType, _that.getMatrixType())) return false;
			if (!Objects.equals(matrixTerm, _that.getMatrixTerm())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (matrixType != null ? matrixType.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (matrixTerm != null ? matrixTerm.getClass().getName().hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "ContractualMatrix {" +
				"matrixType=" + this.matrixType + ", " +
				"matrixTerm=" + this.matrixTerm +
			'}';
		}
	}

	/*********************** Builder Implementation of ContractualMatrix  ***********************/
	class ContractualMatrixBuilderImpl implements ContractualMatrix.ContractualMatrixBuilder {
	
		protected FieldWithMetaMatrixTypeEnum.FieldWithMetaMatrixTypeEnumBuilder matrixType;
		protected FieldWithMetaMatrixTermEnum.FieldWithMetaMatrixTermEnumBuilder matrixTerm;
	
		public ContractualMatrixBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("matrixType")
		public FieldWithMetaMatrixTypeEnum.FieldWithMetaMatrixTypeEnumBuilder getMatrixType() {
			return matrixType;
		}
		
		@Override
		public FieldWithMetaMatrixTypeEnum.FieldWithMetaMatrixTypeEnumBuilder getOrCreateMatrixType() {
			FieldWithMetaMatrixTypeEnum.FieldWithMetaMatrixTypeEnumBuilder result;
			if (matrixType!=null) {
				result = matrixType;
			}
			else {
				result = matrixType = FieldWithMetaMatrixTypeEnum.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("matrixTerm")
		public FieldWithMetaMatrixTermEnum.FieldWithMetaMatrixTermEnumBuilder getMatrixTerm() {
			return matrixTerm;
		}
		
		@Override
		public FieldWithMetaMatrixTermEnum.FieldWithMetaMatrixTermEnumBuilder getOrCreateMatrixTerm() {
			FieldWithMetaMatrixTermEnum.FieldWithMetaMatrixTermEnumBuilder result;
			if (matrixTerm!=null) {
				result = matrixTerm;
			}
			else {
				result = matrixTerm = FieldWithMetaMatrixTermEnum.builder();
			}
			
			return result;
		}
	
		@Override
		@RosettaAttribute("matrixType")
		public ContractualMatrix.ContractualMatrixBuilder setMatrixType(FieldWithMetaMatrixTypeEnum matrixType) {
			this.matrixType = matrixType==null?null:matrixType.toBuilder();
			return this;
		}
		@Override
		public ContractualMatrix.ContractualMatrixBuilder setMatrixTypeValue(MatrixTypeEnum matrixType) {
			this.getOrCreateMatrixType().setValue(matrixType);
			return this;
		}
		@Override
		@RosettaAttribute("matrixTerm")
		public ContractualMatrix.ContractualMatrixBuilder setMatrixTerm(FieldWithMetaMatrixTermEnum matrixTerm) {
			this.matrixTerm = matrixTerm==null?null:matrixTerm.toBuilder();
			return this;
		}
		@Override
		public ContractualMatrix.ContractualMatrixBuilder setMatrixTermValue(MatrixTermEnum matrixTerm) {
			this.getOrCreateMatrixTerm().setValue(matrixTerm);
			return this;
		}
		
		@Override
		public ContractualMatrix build() {
			return new ContractualMatrix.ContractualMatrixImpl(this);
		}
		
		@Override
		public ContractualMatrix.ContractualMatrixBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public ContractualMatrix.ContractualMatrixBuilder prune() {
			if (matrixType!=null && !matrixType.prune().hasData()) matrixType = null;
			if (matrixTerm!=null && !matrixTerm.prune().hasData()) matrixTerm = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getMatrixType()!=null) return true;
			if (getMatrixTerm()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public ContractualMatrix.ContractualMatrixBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			ContractualMatrix.ContractualMatrixBuilder o = (ContractualMatrix.ContractualMatrixBuilder) other;
			
			merger.mergeRosetta(getMatrixType(), o.getMatrixType(), this::setMatrixType);
			merger.mergeRosetta(getMatrixTerm(), o.getMatrixTerm(), this::setMatrixTerm);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			ContractualMatrix _that = getType().cast(o);
		
			if (!Objects.equals(matrixType, _that.getMatrixType())) return false;
			if (!Objects.equals(matrixTerm, _that.getMatrixTerm())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (matrixType != null ? matrixType.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (matrixTerm != null ? matrixTerm.getClass().getName().hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "ContractualMatrixBuilder {" +
				"matrixType=" + this.matrixType + ", " +
				"matrixTerm=" + this.matrixTerm +
			'}';
		}
	}
}
