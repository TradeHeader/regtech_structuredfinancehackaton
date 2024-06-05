package cdm.product.asset;

import cdm.product.asset.SettledEntityMatrix;
import cdm.product.asset.SettledEntityMatrix.SettledEntityMatrixBuilder;
import cdm.product.asset.SettledEntityMatrix.SettledEntityMatrixBuilderImpl;
import cdm.product.asset.SettledEntityMatrix.SettledEntityMatrixImpl;
import cdm.product.asset.SettledEntityMatrixSourceEnum;
import cdm.product.asset.meta.SettledEntityMatrixMeta;
import cdm.product.asset.metafields.FieldWithMetaSettledEntityMatrixSourceEnum;
import cdm.product.asset.metafields.FieldWithMetaSettledEntityMatrixSourceEnum.FieldWithMetaSettledEntityMatrixSourceEnumBuilder;
import com.rosetta.model.lib.RosettaModelObject;
import com.rosetta.model.lib.RosettaModelObjectBuilder;
import com.rosetta.model.lib.annotations.RosettaAttribute;
import com.rosetta.model.lib.annotations.RosettaDataType;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.process.BuilderMerger;
import com.rosetta.model.lib.process.BuilderProcessor;
import com.rosetta.model.lib.process.Processor;
import com.rosetta.model.lib.records.Date;
import java.util.Objects;

import static java.util.Optional.ofNullable;

/**
 * A class to specify the Relevant Settled Entity Matrix.
 * @version ${project.version}
 */
@RosettaDataType(value="SettledEntityMatrix", builder=SettledEntityMatrix.SettledEntityMatrixBuilderImpl.class, version="${project.version}")
public interface SettledEntityMatrix extends RosettaModelObject {

	SettledEntityMatrixMeta metaData = new SettledEntityMatrixMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Relevant settled entity matrix source.
	 */
	FieldWithMetaSettledEntityMatrixSourceEnum getMatrixSource();
	/**
	 * Specifies the publication date of the applicable version of the matrix. When this element is omitted, the Standard Terms Supplement defines rules for which version of the matrix is applicable.
	 */
	Date getPublicationDate();

	/*********************** Build Methods  ***********************/
	SettledEntityMatrix build();
	
	SettledEntityMatrix.SettledEntityMatrixBuilder toBuilder();
	
	static SettledEntityMatrix.SettledEntityMatrixBuilder builder() {
		return new SettledEntityMatrix.SettledEntityMatrixBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends SettledEntityMatrix> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends SettledEntityMatrix> getType() {
		return SettledEntityMatrix.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("matrixSource"), processor, FieldWithMetaSettledEntityMatrixSourceEnum.class, getMatrixSource());
		processor.processBasic(path.newSubPath("publicationDate"), Date.class, getPublicationDate(), this);
	}
	

	/*********************** Builder Interface  ***********************/
	interface SettledEntityMatrixBuilder extends SettledEntityMatrix, RosettaModelObjectBuilder {
		FieldWithMetaSettledEntityMatrixSourceEnum.FieldWithMetaSettledEntityMatrixSourceEnumBuilder getOrCreateMatrixSource();
		FieldWithMetaSettledEntityMatrixSourceEnum.FieldWithMetaSettledEntityMatrixSourceEnumBuilder getMatrixSource();
		SettledEntityMatrix.SettledEntityMatrixBuilder setMatrixSource(FieldWithMetaSettledEntityMatrixSourceEnum matrixSource0);
		SettledEntityMatrix.SettledEntityMatrixBuilder setMatrixSourceValue(SettledEntityMatrixSourceEnum matrixSource1);
		SettledEntityMatrix.SettledEntityMatrixBuilder setPublicationDate(Date publicationDate);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("matrixSource"), processor, FieldWithMetaSettledEntityMatrixSourceEnum.FieldWithMetaSettledEntityMatrixSourceEnumBuilder.class, getMatrixSource());
			processor.processBasic(path.newSubPath("publicationDate"), Date.class, getPublicationDate(), this);
		}
		

		SettledEntityMatrix.SettledEntityMatrixBuilder prune();
	}

	/*********************** Immutable Implementation of SettledEntityMatrix  ***********************/
	class SettledEntityMatrixImpl implements SettledEntityMatrix {
		private final FieldWithMetaSettledEntityMatrixSourceEnum matrixSource;
		private final Date publicationDate;
		
		protected SettledEntityMatrixImpl(SettledEntityMatrix.SettledEntityMatrixBuilder builder) {
			this.matrixSource = ofNullable(builder.getMatrixSource()).map(f->f.build()).orElse(null);
			this.publicationDate = builder.getPublicationDate();
		}
		
		@Override
		@RosettaAttribute("matrixSource")
		public FieldWithMetaSettledEntityMatrixSourceEnum getMatrixSource() {
			return matrixSource;
		}
		
		@Override
		@RosettaAttribute("publicationDate")
		public Date getPublicationDate() {
			return publicationDate;
		}
		
		@Override
		public SettledEntityMatrix build() {
			return this;
		}
		
		@Override
		public SettledEntityMatrix.SettledEntityMatrixBuilder toBuilder() {
			SettledEntityMatrix.SettledEntityMatrixBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(SettledEntityMatrix.SettledEntityMatrixBuilder builder) {
			ofNullable(getMatrixSource()).ifPresent(builder::setMatrixSource);
			ofNullable(getPublicationDate()).ifPresent(builder::setPublicationDate);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			SettledEntityMatrix _that = getType().cast(o);
		
			if (!Objects.equals(matrixSource, _that.getMatrixSource())) return false;
			if (!Objects.equals(publicationDate, _that.getPublicationDate())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (matrixSource != null ? matrixSource.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (publicationDate != null ? publicationDate.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "SettledEntityMatrix {" +
				"matrixSource=" + this.matrixSource + ", " +
				"publicationDate=" + this.publicationDate +
			'}';
		}
	}

	/*********************** Builder Implementation of SettledEntityMatrix  ***********************/
	class SettledEntityMatrixBuilderImpl implements SettledEntityMatrix.SettledEntityMatrixBuilder {
	
		protected FieldWithMetaSettledEntityMatrixSourceEnum.FieldWithMetaSettledEntityMatrixSourceEnumBuilder matrixSource;
		protected Date publicationDate;
	
		public SettledEntityMatrixBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("matrixSource")
		public FieldWithMetaSettledEntityMatrixSourceEnum.FieldWithMetaSettledEntityMatrixSourceEnumBuilder getMatrixSource() {
			return matrixSource;
		}
		
		@Override
		public FieldWithMetaSettledEntityMatrixSourceEnum.FieldWithMetaSettledEntityMatrixSourceEnumBuilder getOrCreateMatrixSource() {
			FieldWithMetaSettledEntityMatrixSourceEnum.FieldWithMetaSettledEntityMatrixSourceEnumBuilder result;
			if (matrixSource!=null) {
				result = matrixSource;
			}
			else {
				result = matrixSource = FieldWithMetaSettledEntityMatrixSourceEnum.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("publicationDate")
		public Date getPublicationDate() {
			return publicationDate;
		}
		
	
		@Override
		@RosettaAttribute("matrixSource")
		public SettledEntityMatrix.SettledEntityMatrixBuilder setMatrixSource(FieldWithMetaSettledEntityMatrixSourceEnum matrixSource) {
			this.matrixSource = matrixSource==null?null:matrixSource.toBuilder();
			return this;
		}
		@Override
		public SettledEntityMatrix.SettledEntityMatrixBuilder setMatrixSourceValue(SettledEntityMatrixSourceEnum matrixSource) {
			this.getOrCreateMatrixSource().setValue(matrixSource);
			return this;
		}
		@Override
		@RosettaAttribute("publicationDate")
		public SettledEntityMatrix.SettledEntityMatrixBuilder setPublicationDate(Date publicationDate) {
			this.publicationDate = publicationDate==null?null:publicationDate;
			return this;
		}
		
		@Override
		public SettledEntityMatrix build() {
			return new SettledEntityMatrix.SettledEntityMatrixImpl(this);
		}
		
		@Override
		public SettledEntityMatrix.SettledEntityMatrixBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public SettledEntityMatrix.SettledEntityMatrixBuilder prune() {
			if (matrixSource!=null && !matrixSource.prune().hasData()) matrixSource = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getMatrixSource()!=null) return true;
			if (getPublicationDate()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public SettledEntityMatrix.SettledEntityMatrixBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			SettledEntityMatrix.SettledEntityMatrixBuilder o = (SettledEntityMatrix.SettledEntityMatrixBuilder) other;
			
			merger.mergeRosetta(getMatrixSource(), o.getMatrixSource(), this::setMatrixSource);
			
			merger.mergeBasic(getPublicationDate(), o.getPublicationDate(), this::setPublicationDate);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			SettledEntityMatrix _that = getType().cast(o);
		
			if (!Objects.equals(matrixSource, _that.getMatrixSource())) return false;
			if (!Objects.equals(publicationDate, _that.getPublicationDate())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (matrixSource != null ? matrixSource.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (publicationDate != null ? publicationDate.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "SettledEntityMatrixBuilder {" +
				"matrixSource=" + this.matrixSource + ", " +
				"publicationDate=" + this.publicationDate +
			'}';
		}
	}
}
