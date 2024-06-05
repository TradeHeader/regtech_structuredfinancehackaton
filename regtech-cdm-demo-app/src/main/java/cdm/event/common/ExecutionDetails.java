package cdm.event.common;

import cdm.base.staticdata.identifier.IdentifiedList;
import cdm.base.staticdata.party.LegalEntity;
import cdm.event.common.ExecutionDetails;
import cdm.event.common.ExecutionDetails.ExecutionDetailsBuilder;
import cdm.event.common.ExecutionDetails.ExecutionDetailsBuilderImpl;
import cdm.event.common.ExecutionDetails.ExecutionDetailsImpl;
import cdm.event.common.ExecutionTypeEnum;
import cdm.event.common.meta.ExecutionDetailsMeta;
import com.rosetta.model.lib.GlobalKey;
import com.rosetta.model.lib.GlobalKey.GlobalKeyBuilder;
import com.rosetta.model.lib.RosettaModelObject;
import com.rosetta.model.lib.RosettaModelObjectBuilder;
import com.rosetta.model.lib.annotations.RosettaAttribute;
import com.rosetta.model.lib.annotations.RosettaDataType;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.process.BuilderMerger;
import com.rosetta.model.lib.process.BuilderProcessor;
import com.rosetta.model.lib.process.Processor;
import com.rosetta.model.metafields.MetaFields;
import java.util.Objects;

import static java.util.Optional.ofNullable;

/**
 * Defines specific attributes that relate to trade executions.
 * @version ${project.version}
 */
@RosettaDataType(value="ExecutionDetails", builder=ExecutionDetails.ExecutionDetailsBuilderImpl.class, version="${project.version}")
public interface ExecutionDetails extends RosettaModelObject, GlobalKey {

	ExecutionDetailsMeta metaData = new ExecutionDetailsMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Identifies the type of execution, e.g. via voice, electronically...
	 */
	ExecutionTypeEnum getExecutionType();
	/**
	 * Represents the venue on which a trade was executed.
	 */
	LegalEntity getExecutionVenue();
	/**
	 * A reference to the package linking the trade with other trades, in case the trade was executed as part of a package (hence this attribute is optional).
	 */
	IdentifiedList getPackageReference();
	/**
	 */
	MetaFields getMeta();

	/*********************** Build Methods  ***********************/
	ExecutionDetails build();
	
	ExecutionDetails.ExecutionDetailsBuilder toBuilder();
	
	static ExecutionDetails.ExecutionDetailsBuilder builder() {
		return new ExecutionDetails.ExecutionDetailsBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends ExecutionDetails> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends ExecutionDetails> getType() {
		return ExecutionDetails.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("executionType"), ExecutionTypeEnum.class, getExecutionType(), this);
		processRosetta(path.newSubPath("executionVenue"), processor, LegalEntity.class, getExecutionVenue());
		processRosetta(path.newSubPath("packageReference"), processor, IdentifiedList.class, getPackageReference());
		processRosetta(path.newSubPath("meta"), processor, MetaFields.class, getMeta());
	}
	

	/*********************** Builder Interface  ***********************/
	interface ExecutionDetailsBuilder extends ExecutionDetails, RosettaModelObjectBuilder {
		LegalEntity.LegalEntityBuilder getOrCreateExecutionVenue();
		LegalEntity.LegalEntityBuilder getExecutionVenue();
		IdentifiedList.IdentifiedListBuilder getOrCreatePackageReference();
		IdentifiedList.IdentifiedListBuilder getPackageReference();
		MetaFields.MetaFieldsBuilder getOrCreateMeta();
		MetaFields.MetaFieldsBuilder getMeta();
		ExecutionDetails.ExecutionDetailsBuilder setExecutionType(ExecutionTypeEnum executionType);
		ExecutionDetails.ExecutionDetailsBuilder setExecutionVenue(LegalEntity executionVenue);
		ExecutionDetails.ExecutionDetailsBuilder setPackageReference(IdentifiedList packageReference);
		ExecutionDetails.ExecutionDetailsBuilder setMeta(MetaFields meta);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("executionType"), ExecutionTypeEnum.class, getExecutionType(), this);
			processRosetta(path.newSubPath("executionVenue"), processor, LegalEntity.LegalEntityBuilder.class, getExecutionVenue());
			processRosetta(path.newSubPath("packageReference"), processor, IdentifiedList.IdentifiedListBuilder.class, getPackageReference());
			processRosetta(path.newSubPath("meta"), processor, MetaFields.MetaFieldsBuilder.class, getMeta());
		}
		

		ExecutionDetails.ExecutionDetailsBuilder prune();
	}

	/*********************** Immutable Implementation of ExecutionDetails  ***********************/
	class ExecutionDetailsImpl implements ExecutionDetails {
		private final ExecutionTypeEnum executionType;
		private final LegalEntity executionVenue;
		private final IdentifiedList packageReference;
		private final MetaFields meta;
		
		protected ExecutionDetailsImpl(ExecutionDetails.ExecutionDetailsBuilder builder) {
			this.executionType = builder.getExecutionType();
			this.executionVenue = ofNullable(builder.getExecutionVenue()).map(f->f.build()).orElse(null);
			this.packageReference = ofNullable(builder.getPackageReference()).map(f->f.build()).orElse(null);
			this.meta = ofNullable(builder.getMeta()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("executionType")
		public ExecutionTypeEnum getExecutionType() {
			return executionType;
		}
		
		@Override
		@RosettaAttribute("executionVenue")
		public LegalEntity getExecutionVenue() {
			return executionVenue;
		}
		
		@Override
		@RosettaAttribute("packageReference")
		public IdentifiedList getPackageReference() {
			return packageReference;
		}
		
		@Override
		@RosettaAttribute("meta")
		public MetaFields getMeta() {
			return meta;
		}
		
		@Override
		public ExecutionDetails build() {
			return this;
		}
		
		@Override
		public ExecutionDetails.ExecutionDetailsBuilder toBuilder() {
			ExecutionDetails.ExecutionDetailsBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(ExecutionDetails.ExecutionDetailsBuilder builder) {
			ofNullable(getExecutionType()).ifPresent(builder::setExecutionType);
			ofNullable(getExecutionVenue()).ifPresent(builder::setExecutionVenue);
			ofNullable(getPackageReference()).ifPresent(builder::setPackageReference);
			ofNullable(getMeta()).ifPresent(builder::setMeta);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			ExecutionDetails _that = getType().cast(o);
		
			if (!Objects.equals(executionType, _that.getExecutionType())) return false;
			if (!Objects.equals(executionVenue, _that.getExecutionVenue())) return false;
			if (!Objects.equals(packageReference, _that.getPackageReference())) return false;
			if (!Objects.equals(meta, _that.getMeta())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (executionType != null ? executionType.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (executionVenue != null ? executionVenue.hashCode() : 0);
			_result = 31 * _result + (packageReference != null ? packageReference.hashCode() : 0);
			_result = 31 * _result + (meta != null ? meta.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "ExecutionDetails {" +
				"executionType=" + this.executionType + ", " +
				"executionVenue=" + this.executionVenue + ", " +
				"packageReference=" + this.packageReference + ", " +
				"meta=" + this.meta +
			'}';
		}
	}

	/*********************** Builder Implementation of ExecutionDetails  ***********************/
	class ExecutionDetailsBuilderImpl implements ExecutionDetails.ExecutionDetailsBuilder, GlobalKeyBuilder {
	
		protected ExecutionTypeEnum executionType;
		protected LegalEntity.LegalEntityBuilder executionVenue;
		protected IdentifiedList.IdentifiedListBuilder packageReference;
		protected MetaFields.MetaFieldsBuilder meta;
	
		public ExecutionDetailsBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("executionType")
		public ExecutionTypeEnum getExecutionType() {
			return executionType;
		}
		
		@Override
		@RosettaAttribute("executionVenue")
		public LegalEntity.LegalEntityBuilder getExecutionVenue() {
			return executionVenue;
		}
		
		@Override
		public LegalEntity.LegalEntityBuilder getOrCreateExecutionVenue() {
			LegalEntity.LegalEntityBuilder result;
			if (executionVenue!=null) {
				result = executionVenue;
			}
			else {
				result = executionVenue = LegalEntity.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("packageReference")
		public IdentifiedList.IdentifiedListBuilder getPackageReference() {
			return packageReference;
		}
		
		@Override
		public IdentifiedList.IdentifiedListBuilder getOrCreatePackageReference() {
			IdentifiedList.IdentifiedListBuilder result;
			if (packageReference!=null) {
				result = packageReference;
			}
			else {
				result = packageReference = IdentifiedList.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("meta")
		public MetaFields.MetaFieldsBuilder getMeta() {
			return meta;
		}
		
		@Override
		public MetaFields.MetaFieldsBuilder getOrCreateMeta() {
			MetaFields.MetaFieldsBuilder result;
			if (meta!=null) {
				result = meta;
			}
			else {
				result = meta = MetaFields.builder();
			}
			
			return result;
		}
	
		@Override
		@RosettaAttribute("executionType")
		public ExecutionDetails.ExecutionDetailsBuilder setExecutionType(ExecutionTypeEnum executionType) {
			this.executionType = executionType==null?null:executionType;
			return this;
		}
		@Override
		@RosettaAttribute("executionVenue")
		public ExecutionDetails.ExecutionDetailsBuilder setExecutionVenue(LegalEntity executionVenue) {
			this.executionVenue = executionVenue==null?null:executionVenue.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("packageReference")
		public ExecutionDetails.ExecutionDetailsBuilder setPackageReference(IdentifiedList packageReference) {
			this.packageReference = packageReference==null?null:packageReference.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("meta")
		public ExecutionDetails.ExecutionDetailsBuilder setMeta(MetaFields meta) {
			this.meta = meta==null?null:meta.toBuilder();
			return this;
		}
		
		@Override
		public ExecutionDetails build() {
			return new ExecutionDetails.ExecutionDetailsImpl(this);
		}
		
		@Override
		public ExecutionDetails.ExecutionDetailsBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public ExecutionDetails.ExecutionDetailsBuilder prune() {
			if (executionVenue!=null && !executionVenue.prune().hasData()) executionVenue = null;
			if (packageReference!=null && !packageReference.prune().hasData()) packageReference = null;
			if (meta!=null && !meta.prune().hasData()) meta = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getExecutionType()!=null) return true;
			if (getExecutionVenue()!=null && getExecutionVenue().hasData()) return true;
			if (getPackageReference()!=null && getPackageReference().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public ExecutionDetails.ExecutionDetailsBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			ExecutionDetails.ExecutionDetailsBuilder o = (ExecutionDetails.ExecutionDetailsBuilder) other;
			
			merger.mergeRosetta(getExecutionVenue(), o.getExecutionVenue(), this::setExecutionVenue);
			merger.mergeRosetta(getPackageReference(), o.getPackageReference(), this::setPackageReference);
			merger.mergeRosetta(getMeta(), o.getMeta(), this::setMeta);
			
			merger.mergeBasic(getExecutionType(), o.getExecutionType(), this::setExecutionType);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			ExecutionDetails _that = getType().cast(o);
		
			if (!Objects.equals(executionType, _that.getExecutionType())) return false;
			if (!Objects.equals(executionVenue, _that.getExecutionVenue())) return false;
			if (!Objects.equals(packageReference, _that.getPackageReference())) return false;
			if (!Objects.equals(meta, _that.getMeta())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (executionType != null ? executionType.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (executionVenue != null ? executionVenue.hashCode() : 0);
			_result = 31 * _result + (packageReference != null ? packageReference.hashCode() : 0);
			_result = 31 * _result + (meta != null ? meta.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "ExecutionDetailsBuilder {" +
				"executionType=" + this.executionType + ", " +
				"executionVenue=" + this.executionVenue + ", " +
				"packageReference=" + this.packageReference + ", " +
				"meta=" + this.meta +
			'}';
		}
	}
}
