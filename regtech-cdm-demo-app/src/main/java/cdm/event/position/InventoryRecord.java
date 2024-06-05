package cdm.event.position;

import cdm.base.staticdata.asset.common.Security;
import cdm.base.staticdata.identifier.AssignedIdentifier;
import cdm.event.position.InventoryRecord;
import cdm.event.position.InventoryRecord.InventoryRecordBuilder;
import cdm.event.position.InventoryRecord.InventoryRecordBuilderImpl;
import cdm.event.position.InventoryRecord.InventoryRecordImpl;
import cdm.event.position.meta.InventoryRecordMeta;
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
 * An individual piece of inventory. This represents a single security.
 * @version ${project.version}
 */
@RosettaDataType(value="InventoryRecord", builder=InventoryRecord.InventoryRecordBuilderImpl.class, version="${project.version}")
public interface InventoryRecord extends RosettaModelObject {

	InventoryRecordMeta metaData = new InventoryRecordMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Unique identifier for this record. This can be used to uniquely identify a specific piece of inventory.
	 */
	AssignedIdentifier getIdentifer();
	/**
	 * The security details.
	 */
	Security getSecurity();

	/*********************** Build Methods  ***********************/
	InventoryRecord build();
	
	InventoryRecord.InventoryRecordBuilder toBuilder();
	
	static InventoryRecord.InventoryRecordBuilder builder() {
		return new InventoryRecord.InventoryRecordBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends InventoryRecord> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends InventoryRecord> getType() {
		return InventoryRecord.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("identifer"), processor, AssignedIdentifier.class, getIdentifer());
		processRosetta(path.newSubPath("security"), processor, Security.class, getSecurity());
	}
	

	/*********************** Builder Interface  ***********************/
	interface InventoryRecordBuilder extends InventoryRecord, RosettaModelObjectBuilder {
		AssignedIdentifier.AssignedIdentifierBuilder getOrCreateIdentifer();
		AssignedIdentifier.AssignedIdentifierBuilder getIdentifer();
		Security.SecurityBuilder getOrCreateSecurity();
		Security.SecurityBuilder getSecurity();
		InventoryRecord.InventoryRecordBuilder setIdentifer(AssignedIdentifier identifer);
		InventoryRecord.InventoryRecordBuilder setSecurity(Security security);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("identifer"), processor, AssignedIdentifier.AssignedIdentifierBuilder.class, getIdentifer());
			processRosetta(path.newSubPath("security"), processor, Security.SecurityBuilder.class, getSecurity());
		}
		

		InventoryRecord.InventoryRecordBuilder prune();
	}

	/*********************** Immutable Implementation of InventoryRecord  ***********************/
	class InventoryRecordImpl implements InventoryRecord {
		private final AssignedIdentifier identifer;
		private final Security security;
		
		protected InventoryRecordImpl(InventoryRecord.InventoryRecordBuilder builder) {
			this.identifer = ofNullable(builder.getIdentifer()).map(f->f.build()).orElse(null);
			this.security = ofNullable(builder.getSecurity()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("identifer")
		public AssignedIdentifier getIdentifer() {
			return identifer;
		}
		
		@Override
		@RosettaAttribute("security")
		public Security getSecurity() {
			return security;
		}
		
		@Override
		public InventoryRecord build() {
			return this;
		}
		
		@Override
		public InventoryRecord.InventoryRecordBuilder toBuilder() {
			InventoryRecord.InventoryRecordBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(InventoryRecord.InventoryRecordBuilder builder) {
			ofNullable(getIdentifer()).ifPresent(builder::setIdentifer);
			ofNullable(getSecurity()).ifPresent(builder::setSecurity);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			InventoryRecord _that = getType().cast(o);
		
			if (!Objects.equals(identifer, _that.getIdentifer())) return false;
			if (!Objects.equals(security, _that.getSecurity())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (identifer != null ? identifer.hashCode() : 0);
			_result = 31 * _result + (security != null ? security.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "InventoryRecord {" +
				"identifer=" + this.identifer + ", " +
				"security=" + this.security +
			'}';
		}
	}

	/*********************** Builder Implementation of InventoryRecord  ***********************/
	class InventoryRecordBuilderImpl implements InventoryRecord.InventoryRecordBuilder {
	
		protected AssignedIdentifier.AssignedIdentifierBuilder identifer;
		protected Security.SecurityBuilder security;
	
		public InventoryRecordBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("identifer")
		public AssignedIdentifier.AssignedIdentifierBuilder getIdentifer() {
			return identifer;
		}
		
		@Override
		public AssignedIdentifier.AssignedIdentifierBuilder getOrCreateIdentifer() {
			AssignedIdentifier.AssignedIdentifierBuilder result;
			if (identifer!=null) {
				result = identifer;
			}
			else {
				result = identifer = AssignedIdentifier.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("security")
		public Security.SecurityBuilder getSecurity() {
			return security;
		}
		
		@Override
		public Security.SecurityBuilder getOrCreateSecurity() {
			Security.SecurityBuilder result;
			if (security!=null) {
				result = security;
			}
			else {
				result = security = Security.builder();
			}
			
			return result;
		}
	
		@Override
		@RosettaAttribute("identifer")
		public InventoryRecord.InventoryRecordBuilder setIdentifer(AssignedIdentifier identifer) {
			this.identifer = identifer==null?null:identifer.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("security")
		public InventoryRecord.InventoryRecordBuilder setSecurity(Security security) {
			this.security = security==null?null:security.toBuilder();
			return this;
		}
		
		@Override
		public InventoryRecord build() {
			return new InventoryRecord.InventoryRecordImpl(this);
		}
		
		@Override
		public InventoryRecord.InventoryRecordBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public InventoryRecord.InventoryRecordBuilder prune() {
			if (identifer!=null && !identifer.prune().hasData()) identifer = null;
			if (security!=null && !security.prune().hasData()) security = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getIdentifer()!=null && getIdentifer().hasData()) return true;
			if (getSecurity()!=null && getSecurity().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public InventoryRecord.InventoryRecordBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			InventoryRecord.InventoryRecordBuilder o = (InventoryRecord.InventoryRecordBuilder) other;
			
			merger.mergeRosetta(getIdentifer(), o.getIdentifer(), this::setIdentifer);
			merger.mergeRosetta(getSecurity(), o.getSecurity(), this::setSecurity);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			InventoryRecord _that = getType().cast(o);
		
			if (!Objects.equals(identifer, _that.getIdentifer())) return false;
			if (!Objects.equals(security, _that.getSecurity())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (identifer != null ? identifer.hashCode() : 0);
			_result = 31 * _result + (security != null ? security.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "InventoryRecordBuilder {" +
				"identifer=" + this.identifer + ", " +
				"security=" + this.security +
			'}';
		}
	}
}
